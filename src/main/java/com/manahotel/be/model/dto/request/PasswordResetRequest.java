package com.manahotel.be.model.dto.request;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
    private int type;
}
