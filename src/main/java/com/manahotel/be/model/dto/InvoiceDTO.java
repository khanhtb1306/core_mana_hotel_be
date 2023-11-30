package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InvoiceDTO {
    private String invoiceId;
    private String customerId;
    private String reservationId;
    private Long createdById;
    private Float total;
    private Float discount;
    private Timestamp createdDate;
    private String status;
    private String note;
}
