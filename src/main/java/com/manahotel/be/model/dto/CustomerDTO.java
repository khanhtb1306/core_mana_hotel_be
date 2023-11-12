package com.manahotel.be.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class CustomerDTO {
    private String customerName;
    private String customerGroupId;
    private String phoneNumber;
    private String dob;
    private String email;
    private String address;
    private String identity;
    private String nationality;
    private String taxCode;
    private boolean gender;
    private MultipartFile image;
}
