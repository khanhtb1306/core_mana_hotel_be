package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodsCategoryDTO {
    private String goodsCategoryId;
    private String goodsCategoryName;
    private boolean status;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
