package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FundBookDTO {
    private String fundBookId;
    private String orderId;
    private Timestamp time;
    private String type;
    private String paidMethod;
    private Float value;
    private Float prepaid;
    private Float paid;
    private String payerReceiver;
    private String staff;
    private String note;
    private String status;
}
