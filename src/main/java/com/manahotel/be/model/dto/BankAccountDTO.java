package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class BankAccountDTO {
    private String bankAccountNumber;
    private String bankAccountName;
    private int bankId;
}
