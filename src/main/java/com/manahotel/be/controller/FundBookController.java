package com.manahotel.be.controller;

import com.manahotel.be.model.dto.FundBookDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.FundBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fund-book")
public class FundBookController {

    @Autowired
    private FundBookService service;

    @GetMapping
    public ResponseDTO getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseDTO createFundBook(FundBookDTO fundBookDTO) {
        return service.createFundBook(fundBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateFundBook(@PathVariable String id, FundBookDTO fundBookDTO) {
        return service.updateFundBook(id, fundBookDTO);
    }

    @GetMapping("/summary")
    public ResponseDTO getFundBookSummary(Integer time, boolean isMonth) {
        return service.getFundBookSummary(time, isMonth);
    }
}
