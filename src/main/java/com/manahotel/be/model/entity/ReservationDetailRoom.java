package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "reservation_detail_room", indexes = {
        @Index(name = "pk_rdr_rd_idx", columnList = "reservation_detail_id"),
        @Index(name = "pk_rdr_c_idx", columnList = "customer_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailRoom {
    @Id
    @Column(name = "reservation_detail_room_id", nullable = false)
    private Long reservationDetailRoomId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_detail_id", nullable = false)
    private ReservationDetail reservationDetail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}