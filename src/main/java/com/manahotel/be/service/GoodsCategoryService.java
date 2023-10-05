package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.model.dto.GoodsCategoryDTO;
import com.manahotel.be.model.entity.GoodsCategory;
import com.manahotel.be.repository.GoodsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository repository;

    public GoodsCategory createGoodsCategory(GoodsCategoryDTO dto) {

        GoodsCategory latestGoodsCategory = repository.findTopByOrderByGoodsCategoryIdDesc();
        String latestId = (latestGoodsCategory == null) ? null : latestGoodsCategory.getGoodsCategoryId();
        String nextId = IdGenerator.generateId(latestId, "LH");

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setGoodsCategoryId(nextId);
        goodsCategory.setGoodsCategoryName(dto.getGoodsCategoryName());
        goodsCategory.setStatus(true);
        goodsCategory.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        repository.save(goodsCategory);

        return goodsCategory;
    }
}
