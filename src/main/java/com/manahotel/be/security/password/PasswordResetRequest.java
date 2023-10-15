package com.manahotel.be.security.password;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
