package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "goods", indexes = {
        @Index(name = "pk_g_gc_idx", columnList = "goods_category_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    @Id
    @Column(name = "goods_id", nullable = false, length = 50)
    private String goodsId;

    @Column(name = "goods_name", length = 250)
    private String goodsName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "goods_category_id", nullable = false)
    private GoodsCategory goodsCategory;

    @Column(name = "status")
    private Long status;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "price")
    private Float price;

    @Column(name = "unit", length = 250)
    private String unit;

    @Column(name = "inventory")
    private Long inventory;

    @Column(name = "min_inventory")
    private Long minInventory;

    @Column(name = "max_inventory")
    private Long maxInventory;

    @Column(name = "note", length = 250)
    private String note;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}