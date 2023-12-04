package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "fund_book")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundBook {
    @Id
    @Column(name = "fund_book_id", nullable = false, length = 50)
    private String fundBookId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "type", length = 250)
    private String type;

    @Column(name = "paid_method", length = 50)
    private String paidMethod;

    @Column(name = "value")
    private Float value;

    @Column(name = "prepaid")
    private Float prepaid;

    @Column(name = "paid")
    private Float paid;

    @Column(name = "payer_receiver", length = 250)
    private String payerReceiver;

    @Column(name = "staff", length = 250)
    private String staff;

    @Column(name = "note", length = 350)
    private String note;

    @Column(name = "status", length = 50)
    private String status;
}