package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "invoice", indexes = {
        @Index(name = "pk_i_c_idx", columnList = "customer_id"),
        @Index(name = "pk_i_r_idx", columnList = "reservation_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @Column(name = "invoice_id", nullable = false, length = 50)
    private String invoiceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "total")
    private Float total;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "note", length = 45)
    private String note;
}