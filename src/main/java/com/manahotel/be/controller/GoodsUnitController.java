package com.manahotel.be.controller;

import com.manahotel.be.model.dto.GoodsUnitDTO;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.service.GoodsUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods-unit")
public class GoodsUnitController {

    @Autowired
    private GoodsUnitService service;

    @GetMapping
    public List<GoodsUnit> getAll() {
        return service.getAll();
    }

    @PostMapping
    public String createGoodsUnit(@RequestBody GoodsUnitDTO goodsUnitDTO) {
        return service.createGoodsUnit(goodsUnitDTO);
    }

    @PutMapping("/{id}")
    public String updateGoodsUnit(@PathVariable Long id, @RequestBody GoodsUnitDTO goodsUnitDTO) {
        return service.updateGoodsUnit(id, goodsUnitDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteGoodsUnit(@PathVariable Long id) {
        return service.deleteGoodsUnit(id);
    }
}
