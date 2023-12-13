package com.manahotel.be.model.dto.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GoodsDTO {
    private String goodsName;
    private boolean goodsCategory;
    private Long status;
    private Long inventory;
    private Long minInventory;
    private Long maxInventory;
    private String note;
    private String description;
    private MultipartFile image;
}
