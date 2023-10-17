package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.GoodsDTO;
import com.manahotel.be.model.dto.GoodsUnitDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GoodsService {

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private GoodsUnitRepository repository2;

    public List<Goods> getAll() {
        return repository.findAll();
    }

    public List<Map<String, Object>> getAllGoodsWithGoodsUnit() {
        List<Object[]> listGoods = repository.findGoodWithGoodUnits();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] goods : listGoods) {
            Goods g = (Goods) goods[0];
            List<GoodsUnit> listGoodsUnit = repository2.findGoodsUnitByGoods(g);
            Map<String, Object> goodsInfo = new HashMap<>();
            goodsInfo.put("goods", g);
            goodsInfo.put("listGoodsUnit", listGoodsUnit.toArray());

            result.add(goodsInfo);
        }
        return result;
    }

    public Map<String, Object> getGoodsWithGoodsUnitById(String id) {
        Goods goods = findGoodsById(id);
        Map<String, Object> goodsInfo = new HashMap<>();
        List<GoodsUnit> listGoodsUnit = repository2.findGoodsUnitByGoods(goods);
        goodsInfo.put("goods", goods);
        goodsInfo.put("listGoodsUnit", listGoodsUnit.toArray());
        return goodsInfo;
    }

    public String createGoods(GoodsDTO dto, GoodsUnitDTO dto2) {
        try {
            log.info("----- Add Goods Start -----");
            Goods latestGoods = repository.findTopByOrderByGoodsIdDesc();
            String latestId = (latestGoods == null) ? null : latestGoods.getGoodsId();
            String nextId = IdGenerator.generateId(latestId, "SP");

            Goods goods = new Goods();
            goods.setGoodsId(nextId);
            goods.setStatus(Status.ACTIVATE);

            commonMapping(goods, dto);

            goods.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            repository.save(goods);
            log.info("----- Add Goods End -----");

            log.info("----- Add Unit Start -----");
            GoodsUnit goodsUnit = new GoodsUnit();
            goodsUnit.setGoods(goods);

            goodsUnit.setGoodsUnitName(dto2.getGoodsUnitName());
            goodsUnit.setCost(dto2.getCost());
            goodsUnit.setPrice(dto2.getPrice());
            goodsUnit.setIsDefault(true);

            repository2.save(goodsUnit);
            log.info("----- Add Unit End -----");
            return "Tạo hàng hóa thành công";
        }
        catch (Exception e) {
            log.info("Can't add goods", e.getMessage());
            return "Tạo hàng hóa thất bại";
        }
    }

    public String updateGoods(String id, GoodsDTO dto, GoodsUnitDTO dto2) {
        try{
            log.info("----- Update Goods Start -----");
            Goods goods = findGoodsById(id);

            if (goods == null) {
                log.info("Can't find the goods");
                return null;
            }

            commonMapping(goods, dto);

            goods.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

            repository.save(goods);
            log.info("----- Update Goods End -----");

            log.info("----- Update Unit Start -----");
            GoodsUnit goodsUnit = repository2.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true);
            goodsUnit.setGoodsUnitName(dto2.getGoodsUnitName());
            goodsUnit.setCost(dto2.getCost());
            goodsUnit.setPrice(dto2.getPrice());

            repository2.save(goodsUnit);
            log.info("----- Update Unit Done -----");

            return "Cập nhật hàng hóa thành công";
        }
        catch (Exception e) {
            log.info("Can't update goods", e.getMessage());
            return "Cập nhật hàng hóa thất bại";
        }
    }

    public String deleteGoods(String id) {

        try {
            log.info("----- Delete Goods Start -----");
            Goods goods = findGoodsById(id);

            if (goods == null) {
                log.info("Can't find the goods");
                return null;
            }

            goods.setStatus(Status.DEACTIVATE);

            repository.save(goods);
            log.info("----- Delete Goods End -----");

            return "Xóa hàng hóa thành công";
        }
        catch (Exception e) {
            log.info("Can't delete goods", e.getMessage());
            return "Xóa hàng hóa thất bại";
        }
    }

    public Goods findGoodsById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private void commonMapping(Goods goods, GoodsDTO dto) throws IOException {
        goods.setGoodsName(dto.getGoodsName());
        goods.setGoodsCategory(dto.isGoodsCategory());
        goods.setInventory(dto.getInventory());
        goods.setMinInventory(dto.getMinInventory());
        goods.setMaxInventory(dto.getMaxInventory());
        goods.setNote(dto.getNote());
        goods.setDescription(dto.getDescription());
        goods.setImage(dto.getImage() != null ? dto.getImage().getBytes() : null);
    }
}
