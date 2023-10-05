package com.manahotel.be.controller;

import com.manahotel.be.model.dto.GoodsDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService service;

    @GetMapping
    public List<Goods> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Goods createGoods(GoodsDTO goodsDTO) {
        return service.createGoods(goodsDTO);
    }

    @PutMapping("/{id}")
    public Goods updateGoods(@PathVariable String id, GoodsDTO goodsDTO) {
        return service.updateGoods(id, goodsDTO);
    }

    @DeleteMapping("/{id}")
    public Goods deleteGoods(@PathVariable String id) {
        return service.deleteGoods(id);
    }

    @GetMapping("/{id}")
    public Goods getGoodsById(@PathVariable String id) {
        return service.findGoodsById(id);
    }
}
