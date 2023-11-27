package com.manahotel.be.controller;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.BankAccountDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.QRCodeRequest;
import com.manahotel.be.model.entity.BankAccount;
import com.manahotel.be.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/qr-code")
public class QRCodeController {

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
    public ResponseEntity<String> generateQRCode(String reservationId, Long bankAccountId, Float amount) {
        BankAccount bankAccount = service.findBankAccount(bankAccountId);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        String transactionCode = "MGD" + dateFormat.format(new Timestamp(System.currentTimeMillis())) + reservationId;

        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setAccountNo(bankAccount.getBankAccountNumber());
        qrCodeRequest.setAccountName(bankAccount.getBankAccountName());
        qrCodeRequest.setAcqId(bankAccount.getBankId());
        qrCodeRequest.setAmount(Math.round(amount));
        qrCodeRequest.setAddInfo(transactionCode);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<QRCodeRequest> entity = new HttpEntity<>(qrCodeRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.vietqr.io/v2/generate",
                HttpMethod.POST,
                entity,
                String.class
        );

        return response;
    }
}
