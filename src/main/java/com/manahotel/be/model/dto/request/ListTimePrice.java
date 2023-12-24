package com.manahotel.be.model.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ListTimePrice {
    private String time;
    private float price;
}
