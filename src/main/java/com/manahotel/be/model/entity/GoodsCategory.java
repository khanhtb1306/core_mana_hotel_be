package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Table(name = "goods_category")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategory {
    @Id
    @Column(name = "goods_category_id", nullable = false, length = 50)
    private String goodsCategoryId;

    @Column(name = "goods_category_name", length = 250)
    private String goodsCategoryName;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}