package com.manahotel.be.model.dto;

import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailDTO {
    private Long id;
    private String orderId;
    private String goodsId;
    private Long quantity;
    private Float price;
    private Long goodsUnitId;
}
