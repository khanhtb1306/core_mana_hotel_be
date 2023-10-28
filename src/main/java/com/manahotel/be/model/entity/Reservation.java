package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "reservation", indexes = {
        @Index(name = "pk_r_c_idx", columnList = "customer_id")
})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @Column(name = "reservation_id", nullable = false, length = 50)
    private String reservationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_adults")
    private Long totalAdults;

    @Column(name = "total_children")
    private Long totalChildren;

    @Column(name = "check_in_estimate")
    private Timestamp checkInEstimate;

    @Column(name = "check_out_estimate")
    private Timestamp checkOutEstimate;

    @Column(name = "check_in_actual")
    private Timestamp checkInActual;

    @Column(name = "check_out_actual")
    private Timestamp checkOutActual;

    @Column(name = "status")
    private Long status;

    @Column(name = "total_deposit")
    private Float totalDeposit;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "staff_check_in")
    private Long staffCheckIn;

    @Column(name = "staff_check_out")
    private Long staffCheckOut;
}