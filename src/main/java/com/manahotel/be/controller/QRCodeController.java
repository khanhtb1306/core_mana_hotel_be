package com.manahotel.be.controller;

import com.manahotel.be.model.dto.BankAccountDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.QRCodeRequest;
import com.manahotel.be.model.dto.request.RequestQrCode;
import com.manahotel.be.model.dto.response.QRCodeResponse;
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
    public ResponseEntity<QRCodeResponse> generateQRCode(RequestQrCode requestQrCode) {
        BankAccount bankAccount = service.findBankAccount(requestQrCode.getBankAccountId());
        String transactionCode = requestQrCode.getTemplate() != null ? requestQrCode.getMessage() : null;
        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setAccountNo(bankAccount.getBankAccountNumber());
        qrCodeRequest.setAccountName(bankAccount.getBankAccountName());
        qrCodeRequest.setAcqId(bankAccount.getBankId());
        qrCodeRequest.setAmount(Math.round(requestQrCode.getAmount()));
        qrCodeRequest.setAddInfo(transactionCode);
        qrCodeRequest.setFormat("text");
        qrCodeRequest.setTemplate(requestQrCode.getTemplate());

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<QRCodeRequest> entity = new HttpEntity<>(qrCodeRequest, headers);

        ResponseEntity<QRCodeResponse> responseEntity = restTemplate.exchange(
                "https://api.vietqr.io/v2/generate",
                HttpMethod.POST,
                entity,
                QRCodeResponse.class
        );

        QRCodeResponse response = responseEntity.getBody();
        if(response != null) {
            response.setTransactionCode(transactionCode);
        }

        return responseEntity;
    }
}
