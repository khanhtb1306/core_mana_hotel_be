package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.GoodsCategoryDTO;
import com.manahotel.be.model.entity.GoodsCategory;
import com.manahotel.be.repository.GoodsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GoodsCategoryService {

    private static final Long ACTIVATE = Status.ACTIVATE.getStatusId();
    private static final Long DEACTIVATE = Status.DEACTIVATE.getStatusId();

    @Autowired
    private GoodsCategoryRepository repository;

    public GoodsCategory createGoodsCategory(GoodsCategoryDTO dto) {

        GoodsCategory latestGoodsCategory = repository.findTopByOrderByGoodsCategoryIdDesc();
        String latestId = (latestGoodsCategory == null) ? null : latestGoodsCategory.getGoodsCategoryId();
        String nextId = IdGenerator.generateId(latestId, "LH");

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setGoodsCategoryId(nextId);
        goodsCategory.setGoodsCategoryName(dto.getGoodsCategoryName());
        goodsCategory.setStatus(ACTIVATE);
        goodsCategory.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goodsCategory);

        return goodsCategory;
    }

    public GoodsCategory updateGoodsCategory(String id, GoodsCategoryDTO dto) {

        GoodsCategory goodsCategory = getGoodsCategoryById(id);

        if(goodsCategory == null) {
            return null;
        }

        goodsCategory.setGoodsCategoryName(dto.getGoodsCategoryName());
        goodsCategory.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goodsCategory);

        return goodsCategory;
    }

    public GoodsCategory deleteGoodsCategory(String id) {

        GoodsCategory goodsCategory = getGoodsCategoryById(id);

        if(goodsCategory == null) {
            return null;
        }

        goodsCategory.setStatus(DEACTIVATE);

        repository.save(goodsCategory);

        return goodsCategory;
    }

    public GoodsCategory getGoodsCategoryById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods Category not found with id " + id));
    }

    public List<GoodsCategory> getAll() {
        return repository.findAll();
    }
}
