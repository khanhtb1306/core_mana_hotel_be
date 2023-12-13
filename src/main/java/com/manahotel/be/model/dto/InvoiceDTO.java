package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class InvoiceDTO {
    private String invoiceId;
    private Float total;
    private Float discount;
    private Float prePail;
    private String note;
    private String paidMethod;
    private String status;
    private Float priceOther;
    private String customerId;
    private String transactionCode;
    private Long usePoint;
}
