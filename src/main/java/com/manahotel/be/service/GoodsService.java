package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.GoodsDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsCategory;
import com.manahotel.be.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    public List<Goods> getAll() {
        return repository.findAll();
    }

    public Goods createGoods(GoodsDTO dto) {

        Goods latestGoods = repository.findTopByOrderByGoodsIdDesc();
        String latestId = (latestGoods == null) ? null : latestGoods.getGoodsId();
        String nextId = IdGenerator.generateId(latestId, "SP");

        Goods goods = new Goods();
        goods.setGoodsId(nextId);
        goods.setGoodsName(dto.getGoodsName());

        GoodsCategory category = goodsCategoryService.getGoodsCategoryById(dto.getGoodsCategoryId());
        goods.setGoodsCategory(category);

        goods.setStatus(true);
        goods.setCost(dto.getCost());
        goods.setPrice(dto.getPrice());
        goods.setUnit(dto.getUnit());
        goods.setInventory(dto.getInventory());
        goods.setMinInventory(dto.getMinInventory());
        goods.setMaxInventory(dto.getMaxInventory());
        goods.setNote(dto.getNote());
        goods.setDescription(dto.getDescription());
        goods.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goods);

        return goods;
    }

    public Goods updateGoods(String id, GoodsDTO dto) {

        Goods goods = findGoodsById(id);

        if(goods == null) {
            return null;
        }

        goods.setGoodsName(dto.getGoodsName());

        GoodsCategory category = goodsCategoryService.getGoodsCategoryById(dto.getGoodsCategoryId());
        goods.setGoodsCategory(category);

        goods.setCost(dto.getCost());
        goods.setPrice(dto.getPrice());
        goods.setUnit(dto.getUnit());
        goods.setInventory(dto.getInventory());
        goods.setMinInventory(dto.getMinInventory());
        goods.setMaxInventory(dto.getMaxInventory());
        goods.setNote(dto.getNote());
        goods.setDescription(dto.getDescription());
        goods.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goods);

        return goods;
    }

    public Goods deleteGoods(String id) {

        Goods goods = findGoodsById(id);

        if(goods == null) {
            return null;
        }

        goods.setStatus(false);

        repository.save(goods);

        return goods;
    }

    public Goods findGoodsById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }
}
