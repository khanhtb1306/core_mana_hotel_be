package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PriceListDetailDTO {
    private String priceListId;
    private String roomCategoryId;
    private Float priceByDay;
    private Float priceByNight;
    private Float priceByHour;
    private Timestamp timeApply;
    private List<String> dayOfWeek;

}