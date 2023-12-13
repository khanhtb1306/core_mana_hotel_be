package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.BankAccountDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.BankAccount;
import com.manahotel.be.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
