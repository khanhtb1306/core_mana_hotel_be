package com.manahotel.be.model.dto;

import java.sql.Timestamp;

public class PriceListDTO {
    private Long priceListId;
    private Long priceListName;
    private String roomCategoryId;
    private Float priceByDay;
    private Float priceByNight;
    private Float priceByHour;
    private boolean status;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
