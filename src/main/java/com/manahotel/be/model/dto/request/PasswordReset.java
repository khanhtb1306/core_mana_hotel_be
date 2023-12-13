package com.manahotel.be.model.dto.request;

import lombok.Data;

@Data
public class PasswordReset {
    private int type;
    private String oldPassword;
    private String newPassword;

}
