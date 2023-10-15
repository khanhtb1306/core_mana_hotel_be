package com.manahotel.be.service;

import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.GoodsUnitDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoodsUnitName(dto.getGoodsUnitName());

        Goods goods = findGoods(dto.getGoodsId());
        goodsUnit.setGoods(goods);

        goodsUnit.setPrice(dto.getPrice());
        goodsUnit.setCost(dto.getCost());
        goodsUnit.setIsDefault(false);

        repository.save(goodsUnit);

        return "Tạo đơn vị thành công";
    }

    public String updateGoodsUnit(Long id, GoodsUnitDTO dto) {
        GoodsUnit goodsUnit = findGoodsUnitById(id);
        goodsUnit.setGoodsUnitName(dto.getGoodsUnitName());
        goodsUnit.setPrice(dto.getPrice());
        goodsUnit.setCost(dto.getCost());

        repository.save(goodsUnit);

        return "Cập nhật đơn vị thành công";
    }

    public String deleteGoodsUnit(Long id) {
        GoodsUnit goodsUnit = findGoodsUnitById(id);
        repository.delete(goodsUnit);

        return "Delete đơn vị thành công";
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
