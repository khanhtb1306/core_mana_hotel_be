package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {
    private String customerId;
    private Long totalAdults;
    private Long totalChildren;
    private String status;
    private Float totalDeposit;
    private Float totalPrice;
    private Timestamp durationStart;
    private Timestamp durationEnd;
    private String note;
}
