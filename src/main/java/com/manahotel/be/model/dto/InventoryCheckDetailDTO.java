package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class InventoryCheckDetailDTO {
    private String goodsId;
    private Long actualInventory;
}
