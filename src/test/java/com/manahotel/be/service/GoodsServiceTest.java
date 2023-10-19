package com.manahotel.be.service;

import com.manahotel.be.model.dto.GoodsDTO;
import com.manahotel.be.model.dto.GoodsUnitDTO;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GoodsServiceTest {

    @Mock
    private GoodsRepository goodsRepository;

    @Mock
    private GoodsUnitRepository goodsUnitRepository;

    @InjectMocks
    private GoodsService goodsService;

    @Test
    void createGoods() {
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
        ResponseEntity<String> result = goodsService.createGoods(goodsDTO, goodsUnitDTO);

        // Assert
        assertEquals("Tạo hàng hóa thành công", result);
    }

    @Test
    void updateGoods() {
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

        // Act
        ResponseEntity<String> result = goodsService.updateGoods(id, goodsDTO, goodsUnitDTO);

        // Assert
        assertEquals("Cập nhật hàng hóa thành công", result);
    }
}