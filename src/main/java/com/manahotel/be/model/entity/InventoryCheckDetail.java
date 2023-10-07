package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "inventory_check_detail", indexes = {
        @Index(name = "pk_icd_g_idx", columnList = "goods_id"),
        @Index(name = "pk_icd_ic_idx", columnList = "inventory_check_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCheckDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_check_detail_id", nullable = false)
    private Long inventoryCheckDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inventory_check_id", nullable = false)
    private InventoryCheck inventoryCheck;

    @ManyToOne(optional = false)
    @JoinColumn(name = "goods_id", nullable = false)
    private Goods goods;

    @Column(name = "actual_inventory")
    private Long actualInventory;

    @Column(name = "quantity_discrepancy")
    private Long quantityDiscrepancy;

    @Column(name = "value_discrepancy")
    private Float valueDiscrepancy;

    @Column(name = "inventory")
    private Long inventory;

    @Column(name = "price")
    private Float price;
}