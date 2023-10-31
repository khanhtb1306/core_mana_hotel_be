package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class ReservationDTO {
    private String customerId;
    private Long totalAdults;
    private Long totalChildren;
    private Long status;
    private Float totalDeposit;
    private Float totalPrice;
    private String note;
}
