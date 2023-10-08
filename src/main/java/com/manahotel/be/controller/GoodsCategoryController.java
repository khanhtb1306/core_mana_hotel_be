package com.manahotel.be.controller;

import com.manahotel.be.model.dto.GoodsCategoryDTO;
import com.manahotel.be.model.entity.GoodsCategory;
import com.manahotel.be.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods-category")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService service;

    @PostMapping
    public String createGoodsCategory(@RequestBody GoodsCategoryDTO goodsCategoryDTO) {
        return service.createGoodsCategory(goodsCategoryDTO);
    }

    @PutMapping("/{id}")
    public String updateGoodsCategory(@PathVariable String id, @RequestBody GoodsCategoryDTO goodsCategoryDTO) {
        return service.updateGoodsCategory(id, goodsCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteGoodsCategory(@PathVariable String id) {
        return service.deleteGoodsCategory(id);
    }

    @GetMapping("/{id}")
    public GoodsCategory getGoodsCategoryById(@PathVariable String id) {
        return service.getGoodsCategoryById(id);
    }

    @GetMapping
    public List<GoodsCategory> getAll() {
        return service.getAll();
    }
}
