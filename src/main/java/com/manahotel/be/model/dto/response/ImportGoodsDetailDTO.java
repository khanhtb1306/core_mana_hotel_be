package com.manahotel.be.model.dto.response;

import lombok.Data;

@Data
public class ImportGoodsDetailDTO {
    private String importGoodsId;
    private String goodsId;
    private Long amount;
    private Float cost;
    private Float total;
}
