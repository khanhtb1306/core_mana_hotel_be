package com.manahotel.be.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class StaffDTO {
    private String username;
    private String password;
    private Long status;
    private String role;
    private Timestamp dob;
    private String address;
    private String email;
    private boolean gender;
    private String identity;
    private String taxCode;
    private String phoneNumber;
    private MultipartFile image;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
