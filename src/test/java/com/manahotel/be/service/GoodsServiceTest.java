package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.response.GoodsDTO;
import com.manahotel.be.model.dto.response.GoodsUnitDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import com.manahotel.be.repository.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private GoodsUnitRepository goodsUnitRepository;

    @Mock
    private StaffRepository staffRepository;

    @InjectMocks
    private GoodsService goodsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createGoods() {
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

        // Arrange
        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setGoodsName("Sữa chua");
        goodsDTO.setGoodsCategory(true);
        goodsDTO.setInventory(100000L);
        goodsDTO.setMinInventory(100L);
        goodsDTO.setMaxInventory(100000L);
        goodsDTO.setNote("");
        goodsDTO.setDescription("");
        goodsDTO.setImage(null);

        GoodsUnitDTO goodsUnitDTO = new GoodsUnitDTO();
        goodsUnitDTO.setGoodsUnitName("Hộp");
        goodsUnitDTO.setCost(27000F);
        goodsUnitDTO.setPrice(30000F);

        // Act
        ResponseEntity<Map<String, String>> result = goodsService.createGoods(goodsDTO, goodsUnitDTO);

        // Assert
        assertEquals("Tạo hàng hóa thành công", result.getBody().get("message"));
    }

    @Test
    void updateGoods() {
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

        // Arrange
        String id = "SP000001";

        GoodsDTO goodsDTO = new GoodsDTO();
        goodsDTO.setGoodsName("Sữa chua");
        goodsDTO.setGoodsCategory(true);
        goodsDTO.setInventory(100000L);
        goodsDTO.setMinInventory(100L);
        goodsDTO.setMaxInventory(100000L);
        goodsDTO.setNote("");
        goodsDTO.setDescription("");
        goodsDTO.setImage(null);

        GoodsUnitDTO goodsUnitDTO = new GoodsUnitDTO();
        goodsUnitDTO.setGoodsUnitName("Hộp");
        goodsUnitDTO.setCost(27000F);
        goodsUnitDTO.setPrice(30000F);

        Goods goods = new Goods();
        goods.setGoodsId(id);

        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoodsUnitName("Vỉ");

        Mockito.when(goodsRepository.findById(id)).thenReturn(java.util.Optional.of(goods));
        Mockito.when(goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true)).thenReturn(goodsUnit);

        // Act
        ResponseEntity<String> result = goodsService.updateGoods(id, goodsDTO, goodsUnitDTO);

        // Assert
        assertEquals("Cập nhật hàng hóa thành công", result.getBody());
    }

    @Test
    void deleteGoods() {

        List<String> listGoodsId = new ArrayList<>();

        // Arrange
        String id = "SP000001";

        listGoodsId.add(id);

        Goods goods = new Goods();
        goods.setGoodsId(id);
        goods.setStatus(Status.ACTIVATE);

        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoodsUnitName("Vỉ");

        Mockito.when(goodsRepository.findById(id)).thenReturn(java.util.Optional.of(goods));

        ResponseEntity<Map<String, String>> result = goodsService.deleteListGoods(listGoodsId);

        assertEquals("Xóa hàng hóa thành công", result.getBody().get("SP000001"));
    }
}