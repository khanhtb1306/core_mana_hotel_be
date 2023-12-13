package com.manahotel.be.model.dto.response;

import lombok.Data;

@Data
public class InventoryCheckDetailDTO {
    private String goodsId;
    private Long actualInventory;
}
