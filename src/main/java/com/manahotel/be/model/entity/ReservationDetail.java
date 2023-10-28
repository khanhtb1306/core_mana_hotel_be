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

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "reservation_type", length = 50)
    private String reservationType;
}