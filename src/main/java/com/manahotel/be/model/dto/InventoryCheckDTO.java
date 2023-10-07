package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InventoryCheckDTO {
    private String inventoryCheckId;
    private Timestamp timeBalance;
    private String note;
    private Long status;
    private Timestamp createdDate;
    private Long createdById;
}
