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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsUnitRepository goodsUnitRepository;

    public ResponseEntity<List<Map<String, Object>>> getAllGoodsWithGoodsUnit() {
        List<Object[]> listGoods = goodsRepository.findGoodWithGoodUnits();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] goods : listGoods) {
            Goods g = (Goods) goods[0];
            List<GoodsUnit> listGoodsUnit = goodsUnitRepository.findGoodsUnitByGoods(g);
            Map<String, Object> goodsInfo = new HashMap<>();
            goodsInfo.put("goods", g);
            goodsInfo.put("listGoodsUnit", listGoodsUnit.toArray());
            result.add(goodsInfo);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    public ResponseEntity<List<Map<String, Object>>> findGoodsByCategory(boolean category) {
        List<Map<String, Object>> result = new ArrayList<>();

        List<Goods> goodsList = goodsRepository.findGoodsByGoodsCategory(category);

        for (Goods good : goodsList) {
            List<GoodsUnit> listGoodsUnit = goodsUnitRepository.findGoodsUnitByGoods(good);
            Map<String, Object> goodsInfo = new HashMap<>();

            for (GoodsUnit goodUnit : listGoodsUnit){
                goodsInfo.put("goods", good);
                goodsInfo.put("goodsUnit", goodUnit);
                result.add(goodsInfo);
            }

        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }


    public ResponseEntity<Map<String, Object>> getGoodsWithGoodsUnitById(String id) {
        Goods goods = findGoodsById(id);
        Map<String, Object> goodsInfo = new HashMap<>();
        List<GoodsUnit> listGoodsUnit = goodsUnitRepository.findGoodsUnitByGoods(goods);
        goodsInfo.put("goods", goods);
        goodsInfo.put("listGoodsUnit", listGoodsUnit.toArray());
        return new ResponseEntity<>(goodsInfo, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> createGoods(GoodsDTO dto, GoodsUnitDTO dto2) {
        try {
            log.info("----- Add Goods Start -----");
            Goods latestGoods = goodsRepository.findTopByOrderByGoodsIdDesc();
            String latestId = (latestGoods == null) ? null : latestGoods.getGoodsId();
            String nextId = IdGenerator.generateId(latestId, "SP");

            Goods goods = new Goods();
            goods.setGoodsId(nextId);
            goods.setStatus(Status.ACTIVATE);

            commonMapping(goods, dto);

            goods.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            goodsRepository.save(goods);
            log.info("----- Add Goods End -----");

            log.info("----- Add Unit Start -----");
            GoodsUnit goodsUnit = new GoodsUnit();
            goodsUnit.setGoods(goods);

            commonMapping2(goodsUnit, dto2);
            goodsUnit.setIsDefault(true);

            goodsUnitRepository.save(goodsUnit);
            log.info("----- Add Unit End -----");

            Map<String, String> response = new HashMap<>();
            response.put("message", "Tạo hàng hóa thành công");
            response.put("goodsId", goods.getGoodsId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't add goods", e.getMessage());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Tạo hàng hóa thất bại");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateGoods(String id, GoodsDTO dto, GoodsUnitDTO dto2) {
        try {
            log.info("----- Update Goods Start -----");
            Goods goods = findGoodsById(id);

            commonMapping(goods, dto);

            goods.setStatus(dto.getStatus() != null ? dto.getStatus() : goods.getStatus());
            goods.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

            goodsRepository.save(goods);
            log.info("----- Update Goods End -----");

            log.info("----- Update Unit Start -----");
            GoodsUnit goodsUnit = goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true);
            commonMapping2(goodsUnit, dto2);

            goodsUnitRepository.save(goodsUnit);
            log.info("----- Update Unit Done -----");

            return new ResponseEntity<>("Cập nhật hàng hóa thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't update goods", e.getMessage());
            return new ResponseEntity<>("Cập nhật hàng hóa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<String> deleteGoods(String id) {

        try {
            log.info("----- Delete Goods Start -----");
            Goods goods = findGoodsById(id);

            goods.setStatus(Status.DELETE);

            goodsRepository.save(goods);
            log.info("----- Delete Goods End -----");

            return new ResponseEntity<>("Xóa hàng hóa thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't delete goods", e.getMessage());
            return new ResponseEntity<>("Xóa hàng hóa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, String>> deleteListGoods(List<String> listGoodsId) {
        Map<String, String> result = new HashMap<>();

        if(listGoodsId == null || listGoodsId.isEmpty()) {
            result.put("error", "Danh sách Id không hợp lệ");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        for (String id : listGoodsId) {
            ResponseEntity<String> deleteResult = deleteGoods(id);
            result.put(id, deleteResult.getBody());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

  
    private Goods findGoodsById(String id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private void commonMapping(Goods goods, GoodsDTO dto) throws IOException {
        goods.setGoodsName((dto.getGoodsName() != null && !dto.getGoodsName().isEmpty()) ? dto.getGoodsName() : goods.getGoodsName());
        goods.setGoodsCategory(dto.isGoodsCategory());
        goods.setInventory(dto.getInventory() != null ? dto.getInventory() : goods.getInventory());
        goods.setMinInventory(dto.getMinInventory() != null ? dto.getMinInventory() : goods.getMinInventory());
        goods.setMaxInventory(dto.getMaxInventory() != null ? dto.getMaxInventory() : goods.getMaxInventory());
        goods.setNote((dto.getNote() != null && !dto.getNote().isEmpty()) ? dto.getNote() : goods.getNote());
        goods.setDescription((dto.getDescription() != null && !dto.getDescription().isEmpty()) ? dto.getDescription() : goods.getDescription());
        goods.setImage(dto.getImage() != null ? dto.getImage().getBytes() : goods.getImage());
    }

    private void commonMapping2(GoodsUnit goodsUnit, GoodsUnitDTO dto2) {
        goodsUnit.setGoodsUnitName(dto2.getGoodsUnitName() != null ? dto2.getGoodsUnitName() : goodsUnit.getGoodsUnitName());
        goodsUnit.setCost(dto2.getCost() != null ? dto2.getCost() : goodsUnit.getCost());
        goodsUnit.setPrice(dto2.getPrice() != null ? dto2.getPrice() : goodsUnit.getPrice());
    }
}
