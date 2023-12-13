package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.request.ImportGoodsRequest;
import com.manahotel.be.service.ImportGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/import-goods")
public class ImportGoodsController {

    @Autowired
    private ImportGoodsService service;

    @GetMapping
    public ResponseDTO getAllImportGoodsWithDetails() {
        return service.getAllImportGoodsWithDetails();
    }

    @PostMapping
    public ResponseDTO createImportGoods(ImportGoodsRequest request) {
        return service.createImportGoods(request.getImportGoodsDTO(), request.getListImportGoodsDetailDTO());
    }

    @PutMapping("/{id}")
    public ResponseDTO updateImportGoods(@PathVariable String id, ImportGoodsRequest request) {
        return service.updateImportGoods(id, request.getImportGoodsDTO(), request.getListImportGoodsDetailDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseDTO cancelImportGoods(@PathVariable String id) {
        return service.cancelImportGoods(id);
    }
}
