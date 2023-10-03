package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "goods")
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @Id
    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_category_id")
    private String goodsCategoryId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "price")
    private Float price;

    @Column(name = "unit")
    private String unit;

    @Column(name = "inventory")
    private Long inventory;

    @Column(name = "min_inventory")
    private Long minInventory;

    @Column(name = "max_inventory")
    private Long maxInventory;

    @Column(name = "note")
    private String note;

    @Column(name = "description")
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
