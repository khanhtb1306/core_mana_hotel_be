package com.manahotel.be.security.request;

import lombok.Data;

@Data
public class PasswordReset {
    private int type;
    private String oldPassword;
    private String newPassword;

}
