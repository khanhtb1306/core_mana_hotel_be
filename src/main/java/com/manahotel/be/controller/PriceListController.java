package com.manahotel.be.controller;

import com.manahotel.be.model.dto.PriceListDTO;
import com.manahotel.be.model.dto.PriceListDetailDTO;
import com.manahotel.be.model.entity.PriceList;
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
    public List<PriceList> getAllPriceList() {
        return priceListService.getAllPriceList();
    }

    @PostMapping
    public ResponseEntity<String> createPriceList(PriceListDTO priceListDTO, List<PriceListDetailDTO> priceListDetailDTOList) {
        return priceListService.createPriceList(priceListDTO, priceListDetailDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePriceList( @PathVariable String id, PriceListDTO priceListDTO, List<PriceListDetailDTO> priceListDetailDTOList) {
        return priceListService.updatePriceList(id, priceListDTO, priceListDetailDTOList);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPriceListById(@PathVariable String id) {
        return priceListService.getPriceListByIdWithPriceListDetailList(id);
    }
}