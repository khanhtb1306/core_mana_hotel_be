package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.GoodsUnitDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoodsUnitServiceTest {

    @Mock
    private GoodsUnitRepository goodsUnitRepository;

    @Mock
    private GoodsRepository goodsRepository;

    @InjectMocks
    private GoodsUnitService goodsUnitService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createGoodsUnit() {

        Goods goods = new Goods();
        goods.setGoodsId("SP000001");

        GoodsUnitDTO goodsUnitDTO = new GoodsUnitDTO();
        goodsUnitDTO.setGoodsUnitName("Bịch");
        goodsUnitDTO.setGoodsId(goods.getGoodsId());
        goodsUnitDTO.setCost(27000F);
        goodsUnitDTO.setPrice(30000F);

        Mockito.when(goodsRepository.findById(goods.getGoodsId())).thenReturn(java.util.Optional.of(goods));

        ResponseEntity<String> result = goodsUnitService.createGoodsUnit(goodsUnitDTO);

        assertEquals("Tạo đơn vị thành công", result.getBody());
    }

    @Test
    void updateGoodsUnit() {

        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoodsUnitId(1L);

        GoodsUnitDTO goodsUnitDTO = new GoodsUnitDTO();
        goodsUnitDTO.setGoodsUnitName("Bịch");
        goodsUnitDTO.setCost(27000F);
        goodsUnitDTO.setPrice(30000F);

        Mockito.when(goodsUnitRepository.findById(goodsUnit.getGoodsUnitId())).thenReturn(java.util.Optional.of(goodsUnit));

        ResponseEntity<String> result = goodsUnitService.updateGoodsUnit(goodsUnit.getGoodsUnitId(), goodsUnitDTO);

        assertEquals("Cập nhật đơn vị thành công", result.getBody());
    }

    @Test
    void deleteGoodsUnit() {

        GoodsUnit goodsUnit = new GoodsUnit();
        goodsUnit.setGoodsUnitId(1L);

        Goods goods = new Goods();
        goods.setGoodsId("SP000001");

        Mockito.when(goodsUnitRepository.findById(goodsUnit.getGoodsUnitId())).thenReturn(java.util.Optional.of(goodsUnit));

        ResponseEntity<String> result = goodsUnitService.deleteGoodsUnit(goods.getGoodsId());

        assertEquals("Xóa đơn vị thành công", result.getBody());
    }
}