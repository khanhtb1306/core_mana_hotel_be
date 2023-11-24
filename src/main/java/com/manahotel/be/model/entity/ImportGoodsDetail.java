package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "import_goods_detail", indexes = {
        @Index(name = "pk_igd_g_idx", columnList = "goods_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportGoodsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_goods_detail_id", nullable = false)
    private Long importGoodsDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "import_goods_id", nullable = false)
    private ImportGoods importGoods;

    @ManyToOne(optional = false)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "total")
    private Float total;
}