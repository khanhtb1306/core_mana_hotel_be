package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.FundBookDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.FundBook;
import com.manahotel.be.repository.FundBookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FundBookServiceTest {

    @Mock
    private FundBookRepository repository;

    @InjectMocks
    private FundBookService service;

    @Test
    void createFundBook() {
        // Mock data
        FundBookDTO fundBookDTO = new FundBookDTO();
        fundBookDTO.setValue(100.0F);

        // Mock repository behavior
        when(repository.findTopByFundBookIdContainingOrderByFundBookIdDesc("TTK")).thenReturn(null);
        when(repository.save(Mockito.any())).thenReturn(new FundBook());

        // Call the service method
        ResponseDTO responseDTO = service.createFundBook(fundBookDTO);

        // Verify the results
        assertEquals("Tạo phiếu thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void createFundBookDeposit() {
        // Mock data
        String reservationId = "RES123";
        Float money = 100.0F;
        String paidMethod = "Cash";
        String transactionCode = "123456";

        // Mock repository behavior
        when(repository.findByFundBookIdContaining(Mockito.any())).thenReturn(null);
        when(repository.save(Mockito.any())).thenReturn(new FundBook());

        // Call the service method
        ResponseDTO responseDTO = service.createFundBookDeposit(reservationId, money, paidMethod, transactionCode);

        // Verify the results
        assertEquals("createFundBookDeposit_isSuccess", responseDTO.getDisplayMessage());
    }

    @Test
    void updateFundBook() {
        // Mock data
        String id = "FB123";
        FundBookDTO fundBookDTO = new FundBookDTO();
        fundBookDTO.setValue(150.0F);

        // Mock repository behavior
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(new FundBook()));
        when(repository.save(Mockito.any())).thenReturn(new FundBook());

        // Call the service method
        ResponseDTO responseDTO = service.updateFundBook(id, fundBookDTO);

        // Verify the results
        assertEquals("Cập nhật phiếu thành công", responseDTO.getDisplayMessage());
    }
}