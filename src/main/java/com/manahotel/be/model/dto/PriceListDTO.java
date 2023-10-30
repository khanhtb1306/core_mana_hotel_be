package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class PriceListDTO {
    private String priceListName;
    private String effectiveTimeStart;
    private String effectiveTimeEnd;
    private Long status;
    private String note;
}