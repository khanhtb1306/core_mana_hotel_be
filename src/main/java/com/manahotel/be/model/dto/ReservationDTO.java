package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {
    private String customerId;
    private String priceListId;
    private Long totalAdults;
    private Long totalChildren;
    private String status;
    private Float totalDeposit;
    private Float totalPrice;
    private String paidMethod;
    private String transactionCode;
    private Timestamp durationStart;
    private Timestamp durationEnd;
    private String note;
}
