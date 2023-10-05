package com.manahotel.be.controller;

import com.manahotel.be.model.dto.GoodsCategoryDTO;
import com.manahotel.be.model.entity.GoodsCategory;
import com.manahotel.be.service.GoodsCategoryService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods-category")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService service;

    @PostMapping
    public GoodsCategory createGoodsCategory(GoodsCategoryDTO goodsCategoryDTO) {
        return service.createGoodsCategory(goodsCategoryDTO);
    }
}
