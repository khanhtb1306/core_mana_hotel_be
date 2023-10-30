package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Size(max = 50)
    @Column(name = "reservation_id", nullable = false, length = 50)
    private String reservationId;

    @Column(name = "total_adults")
    private Long totalAdults;

    @Column(name = "total_children")
    private Long totalChildren;

    @Column(name = "check_in_estimate")
    private Instant checkInEstimate;

    @Column(name = "check_out_estimate")
    private Instant checkOutEstimate;

    @Column(name = "check_in_actual")
    private Instant checkInActual;

    @Column(name = "check_out_actual")
    private Instant checkOutActual;

    @Column(name = "status")
    private Long status;

    @Column(name = "total_deposit")
    private Float totalDeposit;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "staff_check_in")
    private Long staffCheckIn;

    @Column(name = "staff_check_out")
    private Long staffCheckOut;

}