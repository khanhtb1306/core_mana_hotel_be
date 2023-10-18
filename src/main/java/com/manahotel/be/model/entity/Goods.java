package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "goods")
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

    @Column(name = "goods_category")
    private boolean goodsCategory;

    @Column(name = "status")
    private Long status;

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

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}