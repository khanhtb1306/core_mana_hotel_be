package com.manahotel.be.controller;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.PriceListDTO;
import com.manahotel.be.model.dto.PriceListDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.PriceListRequest;
import com.manahotel.be.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<String> updatePriceList(@PathVariable String id, PriceListDTO priceListDTO, List<PriceListDetailDTO> priceListDetailDTOList) {
        return priceListService.updatePriceList(id, priceListDTO, priceListDetailDTOList);
    }

    @GetMapping("/{id}")
    public ResponseDTO getPriceListById(@PathVariable String id) {
        Map<String, Object> priceListInfo = priceListService.getPriceListByIdWithPriceListDetailList(id);
        ResponseDTO responseDTO = ResponseUtils.success(priceListInfo,"GetPriceListByIdSuccessfully");
        return responseDTO;

    }
}