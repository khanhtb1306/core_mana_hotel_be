package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class InventoryCheckDetailDTO {
    private Long inventoryCheckDetailId;
    private String inventoryCheckId;
    private String goodsId;
    private Long actualInventory;
    private Long quantityDiscrepancy;
    private Float valueDiscrepancy;
    private Long inventory;
    private Float price;
}
