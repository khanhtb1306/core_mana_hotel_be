package com.manahotel.be.controller;

import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.service.GoodsRequest;
import com.manahotel.be.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService service;

    @GetMapping
    public List<Map<String, Object>> getAll() {
        return service.getAllGoodsWithGoodsUnit();
    }

    @PostMapping
    public String createGoods(GoodsRequest goodsRequest) {
        return service.createGoods(goodsRequest.getGoodsDTO(), goodsRequest.getGoodsUnitDTO());
    }

    @PutMapping("/{id}")
    public String updateGoods(@PathVariable String id, GoodsRequest goodsRequest) {
        return service.updateGoods(id, goodsRequest.getGoodsDTO(), goodsRequest.getGoodsUnitDTO());
    }

    @DeleteMapping("/{id}")
    public String deleteGoods(@PathVariable String id) {
        return service.deleteGoods(id);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getGoodsById(@PathVariable String id) {
        return service.getGoodsWithGoodsUnitById(id);
    }
}
