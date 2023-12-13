package com.manahotel.be.model.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ImportGoodsDTO {
    private String importGoodsId;
    private Timestamp timeImport;
    private String supplier;
    private Float price;
    private Float paid;
    private Long status;
}
