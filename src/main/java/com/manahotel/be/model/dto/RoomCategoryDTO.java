package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class RoomCategoryDTO {
    private String roomCategoryName;
    private Float priceByDay;
    private Float priceByNight;
    private Float priceByHour;
    private Long roomCapacity;
    private Float roomArea;
    private Long status;
    private String description;
    private Long createdById;
    private Long updatedById;
    private String createdDate;
    private String updatedDate;
}
