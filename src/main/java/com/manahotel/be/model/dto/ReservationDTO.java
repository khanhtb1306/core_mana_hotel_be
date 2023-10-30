package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {
    private String customerId;
    private Long totalAdults;
    private Long totalChildren;
    private Timestamp checkInEstimate;
    private Timestamp checkOutEstimate;
    private Timestamp checkInActual;
    private Timestamp checkOutActual;
    private Long status;
    private Float totalDeposit;
    private Double totalPrice;
}
