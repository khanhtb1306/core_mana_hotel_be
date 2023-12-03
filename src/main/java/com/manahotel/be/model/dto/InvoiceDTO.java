package com.manahotel.be.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class InvoiceDTO {
    private Float total;
    private Float discount;
    private String note;
    private String paidMethod;
    private String transactionCode;
}
