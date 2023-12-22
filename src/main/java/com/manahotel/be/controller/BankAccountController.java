package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.BankAccountDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manahotel.be.model.dto.request.QRCodeRequest;
import com.manahotel.be.model.dto.response.QRCodeResponse;

@RestController
@RequestMapping("/qr-code")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @GetMapping
    public ResponseDTO getAllBankAccount() {
        return service.getAllBankAccount();
    }

    @PostMapping
    public ResponseDTO createBankAccount(BankAccountDTO bankAccountDTO) {
        return service.createBankAccount(bankAccountDTO);
    }

    @PostMapping("/generate")
    public ResponseEntity<QRCodeResponse> generateQRCode(String reservationId, Long bankAccountId, Float amount, String template) {
        return service.generateQRCode( reservationId,  bankAccountId,  amount,  template);
    }
}
