package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.response.ImportGoodsDTO;
import com.manahotel.be.model.dto.response.ImportGoodsDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.model.entity.ImportGoods;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ImportGoodsServiceTest {

    @Mock
    private ImportGoodsRepository importGoodsRepository;

    @Mock
    private ImportGoodsDetailRepository importGoodsDetailRepository;

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private GoodsUnitRepository goodsUnitRepository;

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private FundBookRepository fundBookRepository;

    @Mock
    private OverviewService overviewService;

    @InjectMocks
    private ImportGoodsService importGoodsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createImportGoods() {
        // Mock data
        ImportGoodsDTO importGoodsDTO = new ImportGoodsDTO();
        importGoodsDTO.setStatus(Status.TEMPORARY);
        importGoodsDTO.setTimeImport("2023-12-23");

        List<ImportGoodsDetailDTO> listDetailDTO = new ArrayList<>();
        ImportGoodsDetailDTO detailDTO = new ImportGoodsDetailDTO();
        detailDTO.setGoodsId("fakeGoodsId");
        detailDTO.setAmount(5L);
        listDetailDTO.add(detailDTO);

        // Mock repository behavior
        when(goodsRepository.findById("fakeGoodsId")).thenReturn(Optional.of(new Goods()));
        when(goodsUnitRepository.findById(any())).thenReturn(Optional.of(new GoodsUnit()));
        when(importGoodsDetailRepository.findImportGoodsDetailByImportGoods_ImportGoodsId(any())).thenReturn(new ArrayList<>());
        when(staffRepository.findById(any())).thenReturn(Optional.of(new Staff()));

        // Set up the SecurityContext
        Staff mockStaff = new Staff();
        mockStaff.setStaffId(123L);  // Set the staff ID as needed
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockStaff);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Call the service method
        ResponseDTO responseDTO = importGoodsService.createImportGoods(importGoodsDTO, listDetailDTO);

        // Verify the results
        assertEquals("Tạo phiếu nhập thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void updateImportGoods() {
        Staff mockStaff = new Staff();
        mockStaff.setStaffId(123L);  // Set the staff ID as needed
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockStaff);

        // Set up the SecurityContext to use the mock Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Set the SecurityContext to use the mocked SecurityContext
        SecurityContextHolder.setContext(securityContext);

        // Mock data
        String importGoodsId = "fakeImportGoodsId";
        ImportGoodsDTO importGoodsDTO = new ImportGoodsDTO();
        importGoodsDTO.setStatus(Status.TEMPORARY);
        importGoodsDTO.setTimeImport("2023-12-23");

        List<ImportGoodsDetailDTO> listDetailDTO = new ArrayList<>();
        ImportGoodsDetailDTO detailDTO = new ImportGoodsDetailDTO();
        detailDTO.setGoodsId("fakeGoodsId");
        detailDTO.setAmount(5L);
        listDetailDTO.add(detailDTO);

        // Mock repository behavior
        when(importGoodsRepository.findById(importGoodsId)).thenReturn(Optional.of(new ImportGoods()));
        when(goodsRepository.findById("fakeGoodsId")).thenReturn(Optional.of(new Goods()));
        when(goodsUnitRepository.findById(any())).thenReturn(Optional.of(new GoodsUnit()));

        // Call the service method
        ResponseDTO responseDTO = importGoodsService.updateImportGoods(importGoodsId, importGoodsDTO, listDetailDTO);

        // Verify the results
        assertEquals("Cập nhật phiếu nhập thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void cancelImportGoods() {
        // Mock data
        String importGoodsId = "PN123";

        // Mock repository behavior
        when(importGoodsRepository.findById(importGoodsId)).thenReturn(java.util.Optional.of(new ImportGoods()));
        when(importGoodsDetailRepository.findImportGoodsDetailByImportGoods_ImportGoodsId(importGoodsId)).thenReturn(new ArrayList<>());

        // Call the service method
        ResponseDTO responseDTO = importGoodsService.cancelImportGoods(importGoodsId);

        // Verify the results
        assertEquals("Hủy nhập hàng thất bại", responseDTO.getDisplayMessage());
    }
}