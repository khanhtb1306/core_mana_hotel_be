package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoodsDTO {
    private String goodsId;
    private String goodsName;
    private String goodsCategoryId;
    private Long status;
    private Float cost;
    private Float price;
    private String unit;
    private Long inventory;
    private Long minInventory;
    private Long maxInventory;
    private String note;
    private String description;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
