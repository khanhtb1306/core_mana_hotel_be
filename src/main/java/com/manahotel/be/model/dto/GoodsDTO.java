package com.manahotel.be.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class GoodsDTO {
    private String goodsId;
    private String goodsName;
    private boolean goodsCategory;
    private Long status;
    private Long inventory;
    private Long minInventory;
    private Long maxInventory;
    private String note;
    private String description;
    private MultipartFile image;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
