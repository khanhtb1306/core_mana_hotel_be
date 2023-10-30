package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDetailDTO {
    private String reservationId;
    private String roomId;
    private Timestamp startDate;
    private Timestamp endDate;
    private Float price;
    private String reservationType;

    // Change booking status for room
    private String bookingStatus;
}
