package com.manahotel.be.model.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class BillDTO {
    private String goodsName;
    private Long quantity;
    private Float unitPrice;
    private Float price;
}
