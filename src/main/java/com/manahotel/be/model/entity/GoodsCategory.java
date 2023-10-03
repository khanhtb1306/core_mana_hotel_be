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
@Table(name = "goods_category")
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCategory {
    @Id
    @Column(name = "goods_category_id")
    private String goodsCategoryId;

    @Column(name = "goods_category_name")
    private String goodsCategoryName;

    @Column(name = "status")
    private boolean status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}
