package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class GoodsUnitDTO {
    private String goodsUnitName;
    private String goodsId;
    private Float cost;
    private Float price;
    private boolean isDefault;
}
