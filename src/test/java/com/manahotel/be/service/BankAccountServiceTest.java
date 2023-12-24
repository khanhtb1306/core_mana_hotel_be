package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.BankAccountDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.BankAccount;
import com.manahotel.be.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService;

    @Test
    void createBankAccount() {
        // Mock data
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setBankAccountNumber("123456789");
        bankAccountDTO.setBankAccountName("John Doe");
        bankAccountDTO.setBankId(123456);

        // Mock repository behavior
        when(bankAccountRepository.save(Mockito.any(BankAccount.class))).thenReturn(new BankAccount());

        // Call the service method
        ResponseDTO responseDTO = bankAccountService.createBankAccount(bankAccountDTO);

        // Verify the results
        assertEquals("Tạo tài khoản hưởng thụ thành công", responseDTO.getDisplayMessage());
    }
}