package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.GoodsUnitDTO;
import lombok.Data;

import java.util.List;

@Data
public class GoodsUnitRequest {
    private List<GoodsUnitDTO> goodsUnitDTOs;
}
