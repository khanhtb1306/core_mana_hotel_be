package com.manahotel.be.model.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ListTimePriceResponse {
        private String time;
        private float price;
}
