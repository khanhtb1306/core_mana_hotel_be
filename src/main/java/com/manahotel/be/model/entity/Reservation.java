package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "reservation", indexes = {
        @Index(name = "pk_r_c_idx", columnList = "customer_id"),
        @Index(name = "pk_r_pl_idx", columnList = "price_list_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "reservation_id", nullable = false, length = 50)
    private String reservationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "price_list_id", nullable = false)
    private PriceList priceList;

    @Column(name = "total_adults")
    private Long totalAdults;

    @Column(name = "total_children")
    private Long totalChildren;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "total_deposit")
    private Float totalDeposit;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "paid_method", length = 50)
    private String paidMethod;

    @Column(name = "transaction_code", length = 50)
    private String transactionCode;

    @Column(name = "duration_start")
    private Timestamp durationStart;

    @Column(name = "duration_end")
    private Timestamp durationEnd;

    @Column(name = "note", length = 350)
    private String note;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "staff_check_in")
    private Long staffCheckIn;

    @Column(name = "staff_check_out")
    private Long staffCheckOut;
}