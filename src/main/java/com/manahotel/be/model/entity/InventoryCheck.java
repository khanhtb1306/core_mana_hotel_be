package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "inventory_check")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCheck {
    @Id
    @Column(name = "inventory_check_id", nullable = false, length = 50)
    private String inventoryCheckId;

    @Column(name = "time_balance")
    private Timestamp timeBalance;

    @Column(name = "note", length = 350)
    private String note;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by_id")
    private Long createdById;
}