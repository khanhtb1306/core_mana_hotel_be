package com.manahotel.be.controller;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.request.PriceListRequest;
import com.manahotel.be.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/price-list")
public class PriceListController {

    @Autowired
    private PriceListService priceListService;

    @GetMapping
    public ResponseDTO getAllPriceList() {
        return priceListService.getAllPriceList();
    }

    @PostMapping
    public ResponseDTO createPriceList(PriceListRequest request) {
        return priceListService.createPriceList(request.getPriceListDTO(), request.getPriceListDetailDTO());
    }

    @PutMapping("/{id}")
    public ResponseDTO updatePriceList(@PathVariable String id, PriceListRequest request) {
        return priceListService.updatePriceList(id, request.getPriceListDTO(), request.getPriceListDetailDTO());
    }

    @GetMapping("/{id}")
    public ResponseDTO getPriceListById(@PathVariable String id) {
        Map<String, Object> priceListInfo = priceListService.getPriceListByIdWithPriceListDetailList(id);
        ResponseDTO responseDTO = ResponseUtils.success(priceListInfo,"GetPriceListByIdSuccessfully");
        return responseDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deletePriceListById(@PathVariable String id) {
        return priceListService.deletePriceListById(id);
    }
}