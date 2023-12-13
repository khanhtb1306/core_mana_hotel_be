package com.manahotel.be.model.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InventoryCheckDTO {
    private Timestamp timeBalance;
    private String note;
    private Long status;
}
