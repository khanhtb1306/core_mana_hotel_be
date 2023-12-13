package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.PriceListDTO;
import com.manahotel.be.model.dto.response.PriceListDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.PriceListDetail;
import com.manahotel.be.repository.PriceListDetailRepository;
import com.manahotel.be.repository.PriceListRepository;
import com.manahotel.be.repository.RoomClassRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceListServiceTest {

    @InjectMocks
    private PriceListService priceListService;

    @Mock
    private PriceListRepository priceListRepository;

    @Mock
    private PriceListDetailRepository priceListDetailRepository;

    @Mock
    private RoomClassRepository roomClassRepository;

    @Test
    void createPriceList() {
        // Mock data
        PriceListDTO priceListDTO = new PriceListDTO();
        List<PriceListDetailDTO> priceListDetailDTOList = new ArrayList<>();

        // Mock repository methods
        when(priceListRepository.findTopByOrderByPriceListIdDesc()).thenReturn(null);
        when(priceListRepository.save(any())).thenReturn(new PriceList());

        when(priceListDetailRepository.save(any())).thenReturn(new PriceListDetail());

        // Perform the test
        ResponseDTO responseDTO = priceListService.createPriceList(priceListDTO, priceListDetailDTOList);

        // Verify the result
        assertEquals("Thêm bảng giá mới thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void updatePriceList() {
        // Mock data
        String priceListId = "BG123";
        PriceListDTO priceListDTO = new PriceListDTO();
        List<PriceListDetailDTO> priceListDetailDTOList = new ArrayList<>();

        // Mock repository methods
        when(priceListRepository.findById(priceListId)).thenReturn(java.util.Optional.of(new PriceList()));
        when(priceListRepository.save(any())).thenReturn(new PriceList());

        when(priceListDetailRepository.getAllPriceListDetailByPriceListId(priceListId)).thenReturn(new ArrayList<>());
        when(priceListDetailRepository.save(any())).thenReturn(new PriceListDetail());

        // Perform the test
        ResponseDTO responseDTO = priceListService.updatePriceList(priceListId, priceListDTO, priceListDetailDTOList);

        // Verify the result
        assertEquals("Cập nhật bảng giá thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deletePriceListById() {
        // Mock data
        String priceListId = "BG123";

        // Mock repository methods
        when(priceListRepository.findById(priceListId)).thenReturn(java.util.Optional.of(new PriceList()));
        when(priceListRepository.save(any())).thenReturn(new PriceList());

        // Perform the test
        ResponseDTO responseDTO = priceListService.deletePriceListById(priceListId);

        // Verify the result
        assertEquals(priceListId + "Delete Price List Successfully", responseDTO.getDisplayMessage());
    }
}
