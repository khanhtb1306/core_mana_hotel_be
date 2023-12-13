package com.manahotel.be.model.dto.response;

import lombok.Data;

@Data
public class BankAccountDTO {
    private String bankAccountNumber;
    private String bankAccountName;
    private int bankId;
}
