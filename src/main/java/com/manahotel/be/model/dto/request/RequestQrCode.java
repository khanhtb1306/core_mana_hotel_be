package com.manahotel.be.model.dto.request;

import lombok.Data;

@Data
public class RequestQrCode {
    private  String message;
    private Long bankAccountId;
    private Float amount;
    private String template;
}
