package com.manahotel.be.model.dto.response;

import lombok.Data;


@Data
public class ImportGoodsDTO {
    private String importGoodsId;
    private String timeImport;
    private String supplier;
    private Float price;
    private Float paid;
    private Long status;
}
