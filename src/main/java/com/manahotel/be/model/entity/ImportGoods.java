package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "import_goods")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportGoods {
    @Id
    @Column(name = "import_goods_id", nullable = false, length = 50)
    private String importGoodsId;

    @Column(name = "time_import")
    private Timestamp timeImport;

    @Column(name = "supplier", length = 250)
    private String supplier;

    @Column(name = "price")
    private Float price;

    @Column(name = "paid")
    private Float paid;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_by_id")
    private Long createdById;
}