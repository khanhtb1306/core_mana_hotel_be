package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDetailDTO {
    private String reservationId;
    private String roomId;
    private Timestamp checkInEstimate;
    private Timestamp checkOutEstimate;
    private Timestamp checkInActual;
    private Timestamp checkOutActual;
    private Float price;
    private String reservationType;
    private String status;
}
