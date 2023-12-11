package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FundBookDTO {
    private String fundBookId;
    private String time;
    private String type;
    private String paidMethod;
    private Float value;
    private String payerReceiver;
    private String staff;
    private String note;
    private String status;
    private String transactionCode;
}
