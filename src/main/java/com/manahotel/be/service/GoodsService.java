package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.GoodsDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsCategory;
import com.manahotel.be.repository.GoodsCategoryRepository;
import com.manahotel.be.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GoodsService {

    private static final Long ACTIVATE = Status.ACTIVATE.getStatusId();
    private static final Long DEACTIVATE = Status.DEACTIVATE.getStatusId();

    @Autowired
    private GoodsRepository repository;

    @Autowired
    private GoodsCategoryRepository repository2;

    public List<Goods> getAll() {
        return repository.findAll();
    }

    public Goods createGoods(GoodsDTO dto) {

        Goods latestGoods = repository.findTopByOrderByGoodsIdDesc();
        String latestId = (latestGoods == null) ? null : latestGoods.getGoodsId();
        String nextId = IdGenerator.generateId(latestId, "SP");

        Goods goods = new Goods();
        goods.setGoodsId(nextId);
        goods.setStatus(ACTIVATE);

        commonMapping(goods, dto);

        goods.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goods);

        return goods;
    }

    public Goods updateGoods(String id, GoodsDTO dto) {

        Goods goods = findGoodsById(id);

        if(goods == null) {
            return null;
        }

        commonMapping(goods, dto);

        goods.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goods);

        return goods;
    }

    public Goods deleteGoods(String id) {

        Goods goods = findGoodsById(id);

        if(goods == null) {
            return null;
        }

        goods.setStatus(DEACTIVATE);

        repository.save(goods);

        return goods;
    }

    public Goods findGoodsById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private void commonMapping(Goods goods, GoodsDTO dto) {
        goods.setGoodsName(dto.getGoodsName());

        GoodsCategory category = findGoodsCategory(dto.getGoodsCategoryId());
        goods.setGoodsCategory(category);

        goods.setCost(dto.getCost());
        goods.setPrice(dto.getPrice());
        goods.setUnit(dto.getUnit());
        goods.setInventory(dto.getInventory());
        goods.setMinInventory(dto.getMinInventory());
        goods.setMaxInventory(dto.getMaxInventory());
        goods.setNote(dto.getNote());
        goods.setDescription(dto.getDescription());
    }

    private GoodsCategory findGoodsCategory(String id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods Category not found with id " + id));
    }
}
