package com.manahotel.be.service;

import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.model.dto.response.InventoryCheckDTO;
import com.manahotel.be.model.dto.response.InventoryCheckDetailDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.model.entity.InventoryCheck;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InventoryCheckServiceTest {

    @Mock
    private InventoryCheckRepository inventoryCheckRepository;

    @Mock
    private InventoryCheckDetailRepository inventoryCheckDetailRepository;

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private GoodsUnitRepository goodsUnitRepository;

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private InventoryCheckService inventoryCheckService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createInventoryCheck() {

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

        when(staffRepository.findById(123L)).thenReturn(Optional.of(mockStaff));
        when(staffRepository.save(any())).thenReturn(mockStaff);

        Goods goods = new Goods();
        goods.setGoodsId("SP000001");
        goods.setInventory(100000L);

        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoods(goods);
        goodsUnit.setIsDefault(true);
        goodsUnit.setCost(27000F);
        goodsUnit.setPrice(30000F);

        Goods goods2 = new Goods();
        goods2.setGoodsId("SP000002");
        goods2.setInventory(100000L);

        GoodsUnit goodsUnit2 = new GoodsUnit();
        goodsUnit2.setGoods(goods2);
        goodsUnit2.setIsDefault(true);
        goodsUnit2.setCost(27000F);
        goodsUnit2.setPrice(30000F);

        InventoryCheckDTO inventoryCheckDTO = new InventoryCheckDTO();
        inventoryCheckDTO.setStatus(5L);

        List<InventoryCheckDetailDTO> list = new ArrayList<>();

        InventoryCheckDetailDTO dto = new InventoryCheckDetailDTO();
        dto.setGoodsId(goods.getGoodsId());
        dto.setActualInventory(110000L);

        InventoryCheckDetailDTO dto2 = new InventoryCheckDetailDTO();
        dto2.setGoodsId(goods2.getGoodsId());
        dto2.setActualInventory(120000L);

        list.add(dto);
        list.add(dto2);

        InventoryCheck inventoryCheck = new InventoryCheck();
        inventoryCheck.setInventoryCheckId("KK000001");

        when(goodsRepository.findById(goods.getGoodsId())).thenReturn(java.util.Optional.of(goods));
        when(goodsRepository.findById(goods2.getGoodsId())).thenReturn(java.util.Optional.of(goods2));
        when(inventoryCheckRepository.findById(inventoryCheck.getInventoryCheckId())).thenReturn(java.util.Optional.of(inventoryCheck));
        when(goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true)).thenReturn(goodsUnit);
        when(goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods2.getGoodsId(), true)).thenReturn(goodsUnit2);


        ResponseEntity<String> result = inventoryCheckService.createInventoryCheck(inventoryCheckDTO, list);
        assertEquals("Tạo kiểm kho thất bại", result.getBody());

    }

    @Test
    void updateInventoryCheck() {

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

        when(staffRepository.findById(123L)).thenReturn(Optional.of(mockStaff));
        when(staffRepository.save(any())).thenReturn(mockStaff);

        Goods goods = new Goods();
        goods.setGoodsId("SP000001");
        goods.setInventory(100000L);

        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoods(goods);
        goodsUnit.setIsDefault(true);
        goodsUnit.setCost(27000F);
        goodsUnit.setPrice(30000F);

        Goods goods2 = new Goods();
        goods2.setGoodsId("SP000002");
        goods2.setInventory(100000L);

        GoodsUnit goodsUnit2 = new GoodsUnit();
        goodsUnit2.setGoods(goods2);
        goodsUnit2.setIsDefault(true);
        goodsUnit2.setCost(27000F);
        goodsUnit2.setPrice(30000F);

        InventoryCheckDTO inventoryCheckDTO = new InventoryCheckDTO();
        inventoryCheckDTO.setStatus(5L);

        List<InventoryCheckDetailDTO> list = new ArrayList<>();

        InventoryCheckDetailDTO dto = new InventoryCheckDetailDTO();
        dto.setGoodsId(goods.getGoodsId());
        dto.setActualInventory(110000L);

        InventoryCheckDetailDTO dto2 = new InventoryCheckDetailDTO();
        dto2.setGoodsId(goods2.getGoodsId());
        dto2.setActualInventory(120000L);

        list.add(dto);
        list.add(dto2);

        InventoryCheck inventoryCheck = new InventoryCheck();
        inventoryCheck.setInventoryCheckId("KK000001");

        when(goodsRepository.findById(goods.getGoodsId())).thenReturn(java.util.Optional.of(goods));
        when(goodsRepository.findById(goods2.getGoodsId())).thenReturn(java.util.Optional.of(goods2));
        when(inventoryCheckRepository.findById(inventoryCheck.getInventoryCheckId())).thenReturn(java.util.Optional.of(inventoryCheck));
        when(goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true)).thenReturn(goodsUnit);
        when(goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods2.getGoodsId(), true)).thenReturn(goodsUnit2);

        ResponseEntity<String> result = inventoryCheckService.updateInventoryCheck(inventoryCheck.getInventoryCheckId(), inventoryCheckDTO, list);
        assertEquals("Cập nhật kiểm kho thất bại", result.getBody());
    }

    @Test
    void cancelCheck() {
        InventoryCheck inventoryCheck = new InventoryCheck();
        inventoryCheck.setInventoryCheckId("KK000001");

        when(inventoryCheckRepository.findById(inventoryCheck.getInventoryCheckId())).thenReturn(java.util.Optional.of(inventoryCheck));

        ResponseEntity<String> result = inventoryCheckService.cancelCheck(inventoryCheck.getInventoryCheckId());
        assertEquals("Hủy kiểm kho thành công", result.getBody());
    }
}