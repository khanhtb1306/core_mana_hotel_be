package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "invoice_reservation_detail", indexes = {
        @Index(name = "pk_ird_i", columnList = "invoice_id"),
        @Index(name = "pk_ird_rd", columnList = "reservation_detail_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_reservation_detail_id")
    private Long invoiceReservationDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_detail_id", nullable = false)
    private ReservationDetail reservationDetail;
}
