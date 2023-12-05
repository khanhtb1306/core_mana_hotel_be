package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "invoice", indexes = {
        @Index(name = "pk_i_c_idx", columnList = "customer_id"),
        @Index(name = "pk_i_stf", columnList = "staff_id")
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
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @Column(name = "total")
    private Float total;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "note", length = 250)
    private String note;

    @Column(name = "paid_method", length = 50)
    private String paidMethod;

    @Column(name = "transaction_code", length = 50)
    private String transactionCode;

    @Column(name = "price_other")
    private Float priceOther;
}