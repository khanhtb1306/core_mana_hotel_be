package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.request.QRCodeRequest;
import com.manahotel.be.model.dto.response.BankAccountDTO;
import com.manahotel.be.model.dto.response.QRCodeResponse;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.BankAccount;
import com.manahotel.be.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Slf4j
@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    public ResponseDTO getAllBankAccount() {
        return ResponseUtils.success(repository.findAll(), "Hiển thị danh sách tài khoản hưởng thụ thành công");
    }

    public ResponseDTO createBankAccount(BankAccountDTO bankAccountDTO) {
        try {
            log.info("----- Start create bank account -----");
            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankAccountNumber(bankAccountDTO.getBankAccountNumber());
            bankAccount.setBankAccountName(bankAccountDTO.getBankAccountName());
            bankAccount.setBankId(bankAccountDTO.getBankId());
            repository.save(bankAccount);
            log.info("----- End create bank account -----");

            return ResponseUtils.success(bankAccount.getBankAccountId(), "Tạo tài khoản hưởng thụ thành công");
        }
        catch (Exception e) {
            log.info("----- Create bank account failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo tài khoản hưởng thụ thất bại");
        }
    }

    public BankAccount findBankAccount(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
    }

    public ResponseEntity<QRCodeResponse> generateQRCode(String reservationId, Long bankAccountId, Float amount, String template) {
        BankAccount bankAccount = findBankAccount(bankAccountId);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        String transactionCode = "MGD" + dateFormat.format(new Timestamp(System.currentTimeMillis())) + reservationId;

        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setAccountNo(bankAccount.getBankAccountNumber());
        qrCodeRequest.setAccountName(bankAccount.getBankAccountName());
        qrCodeRequest.setAcqId(bankAccount.getBankId());
        qrCodeRequest.setAmount(Math.round(amount));
        qrCodeRequest.setAddInfo(transactionCode);
        qrCodeRequest.setFormat("text");
        qrCodeRequest.setTemplate(template);

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
