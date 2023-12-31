package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.request.GoodsRequest;
import com.manahotel.be.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService service;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        return service.getAllGoodsWithGoodsUnit();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createGoods(GoodsRequest goodsRequest) {
        return service.createGoods(goodsRequest.getGoodsDTO(), goodsRequest.getGoodsUnitDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGoods(@PathVariable String id, GoodsRequest goodsRequest) {
        return service.updateGoods(id, goodsRequest.getGoodsDTO(), goodsRequest.getGoodsUnitDTO());
    }

    @DeleteMapping("/{listGoodsId}")
    public ResponseEntity<Map<String, String>> deleteListGoods(@PathVariable List<String> listGoodsId) {
        return service.deleteListGoods(listGoodsId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getGoodsById(@PathVariable String id) {
        return service.getGoodsWithGoodsUnitById(id);
    }

    @GetMapping("/category/{category}")
    public ResponseDTO getGoodsByCategory(@PathVariable boolean category){
        return service.findGoodsByCategory(category);
    }
}
