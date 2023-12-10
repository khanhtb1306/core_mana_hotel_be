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
    public ResponseDTO getAll(String time, boolean isMonth) {
        return service.getAll(time, isMonth);
    }

    @PostMapping
    public ResponseDTO createFundBook(FundBookDTO fundBookDTO) {
        return service.createFundBook(fundBookDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateFundBook(@PathVariable String id, FundBookDTO fundBookDTO) {
        return service.updateFundBook(id, fundBookDTO);
    }
    @GetMapping("/by_staff_name")
    public ResponseDTO getFundBookByStaff() {
        return service.getFundBookByStaff();
    }
    @PostMapping("/create_fund_book_by_deposit")
    public ResponseDTO createFundBookDeposit(String reservationId, Float money, String paidMethod) {
        return service.createFundBookDeposit(reservationId, money, paidMethod);
    }
    @GetMapping("/summary")
    public ResponseDTO getFundBookSummary(String time, boolean isMonth) {
        return service.getFundBookSummary(time, isMonth);
    }
}
