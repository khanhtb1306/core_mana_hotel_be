package com.manahotel.be.model.dto.response;

import com.manahotel.be.common.constant.Role;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class StaffDTO {
    private Long staffId;
    private String staffName;
    private String username;
    private String password;
    private String dob;
    private String address;
    private String email;
    private boolean gender;
    private String identity;
    private String taxCode;
    private String phoneNumber;
    private MultipartFile image;
    private String departmentId;
    private Role role;
}
