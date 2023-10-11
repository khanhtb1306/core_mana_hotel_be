package com.manahotel.be.service;

import com.manahotel.be.model.dto.GoodsDTO;
import com.manahotel.be.model.dto.GoodsUnitDTO;
import lombok.Data;

@Data
public class GoodsRequest {
    private GoodsDTO goodsDTO;
    private GoodsUnitDTO goodsUnitDTO;
}
