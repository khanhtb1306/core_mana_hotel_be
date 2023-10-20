package com.manahotel.be.security.request;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private String newPassword;
}
