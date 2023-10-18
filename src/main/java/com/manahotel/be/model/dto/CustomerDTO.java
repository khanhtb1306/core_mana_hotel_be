package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerDTO {
    private String customerName;
    private String customerGroup;
    private String phoneNumber;
    private Timestamp dob;
    private String email;
    private String address;
    private String identity;
    private String nationality;
    private String taxCode;
    private boolean gender;
}
