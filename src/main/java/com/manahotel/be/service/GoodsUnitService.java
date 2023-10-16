package com.manahotel.be.service;

import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.GoodsUnitDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsUnitService {

    @Autowired
    private GoodsUnitRepository repository;

    @Autowired
    private GoodsRepository repository2;

    public List<GoodsUnit> getAll() {
        return repository.findAll();
    }

    public String createGoodsUnit(GoodsUnitDTO dto) {
        try {
            log.info("----- Add Unit Start -----");
            GoodsUnit goodsUnit = new GoodsUnit();
            goodsUnit.setGoodsUnitName(dto.getGoodsUnitName());

            Goods goods = findGoods(dto.getGoodsId());
            goodsUnit.setGoods(goods);

            goodsUnit.setPrice(dto.getPrice());
            goodsUnit.setCost(dto.getCost());
            goodsUnit.setIsDefault(false);

            repository.save(goodsUnit);
            log.info("----- Add Unit End -----");

            return "Tạo đơn vị thành công";
        }
        catch(Exception e) {
            log.info("Can't add unit", e.getMessage());
            return "Tạo đơn vị thất bại";
        }
    }

    public String updateGoodsUnit(Long id, GoodsUnitDTO dto) {
        try {
            log.info("----- Update Unit Start -----");
            GoodsUnit goodsUnit = findGoodsUnitById(id);
            goodsUnit.setGoodsUnitName(dto.getGoodsUnitName());
            goodsUnit.setPrice(dto.getPrice());
            goodsUnit.setCost(dto.getCost());

            repository.save(goodsUnit);
            log.info("----- Update Unit End -----");

            return "Cập nhật đơn vị thành công";
        }
        catch (Exception e) {
            log.info("Can't update unit", e.getMessage());
            return "Cập nhật đơn vị thất bại";
        }
    }

    public String deleteGoodsUnit(Long id) {
        try {
            log.info("----- Delete Unit Start -----");
            GoodsUnit goodsUnit = findGoodsUnitById(id);
            repository.delete(goodsUnit);
            log.info("----- Delete Unit End -----");

            return "Xóa đơn vị thành công";
        }
        catch (Exception e) {
            log.info("Can't delete unit", e.getMessage());
            return  "Xóa đơn vị thất bại";
        }
    }

    private Goods findGoods(String id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private GoodsUnit findGoodsUnitById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods Unit not found with id " + id));
    }
}
