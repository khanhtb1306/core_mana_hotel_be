package com.manahotel.be.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class RoomCategoryDTO {
    private String roomCategoryName;
    private Float priceByDay;
    private Float priceByNight;
    private Float priceByHour;
    private Long numOfAdults;
    private Long numOfChildren;
    private Float roomArea;
    private Long status;
    private String description;
    private MultipartFile image;
    private Long createdById;
    private Long updatedById;
}
