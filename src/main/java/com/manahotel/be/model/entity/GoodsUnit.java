package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "goods_unit", indexes = {
        @Index(name = "pk_gu_g_idx", columnList = "goods_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_unit_id", nullable = false)
    private Long goodsUnitId;

    @Column(name = "goods_unit_name", length = 250)
    private String goodsUnitName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "price")
    private Float price;

    @Column(name = "is_default")
    private Boolean isDefault;
}