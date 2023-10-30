package com.manahotel.be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private boolean isSuccess = true;
    private Object result;
    private String displayMessage;
}
