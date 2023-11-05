package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "reservation_detail", indexes = {
        @Index(name = "pk__idx", columnList = "reservation_id"),
        @Index(name = "pk_rd_r_idx", columnList = "room_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_detail_id", nullable = false)
    private Long reservationDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "check_in_estimate")
    private Timestamp checkInEstimate;

    @Column(name = "check_out_estimate")
    private Timestamp checkOutEstimate;

    @Column(name = "check_in_actual")
    private Timestamp checkInActual;

    @Column(name = "check_out_actual")
    private Timestamp checkOutActual;

    @Column(name = "price")
    private Float price;

    @Column(name = "reservation_type", length = 50)
    private String reservationType;

    @Column(name = "status", length = 50)
    private String status;
}