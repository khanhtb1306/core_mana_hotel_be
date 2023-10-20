package com.manahotel.be.controller;

import com.manahotel.be.model.dto.GoodsUnitDTO;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.service.GoodsUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods-unit")
public class GoodsUnitController {

    @Autowired
    private GoodsUnitService service;

    @GetMapping
    public ResponseEntity<List<GoodsUnit>> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<String> createGoodsUnit(GoodsUnitDTO goodsUnitDTO) {
        return service.createGoodsUnit(goodsUnitDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGoodsUnit(@PathVariable Long id, GoodsUnitDTO goodsUnitDTO) {
        return service.updateGoodsUnit(id, goodsUnitDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoodsUnit(@PathVariable String id) {
        return service.deleteGoodsUnit(id);
    }
}
