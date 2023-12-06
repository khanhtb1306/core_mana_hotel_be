package com.manahotel.be.model.dto.response;

import lombok.Data;

@Data
public class FundBookResponse {
    private Float openingBalance;
    private Float allIncome;
    private Float allExpense;
    private Float fundBalance;
}
