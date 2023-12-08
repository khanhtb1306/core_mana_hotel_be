package com.manahotel.be.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RoomDTO {
    private String roomName;
    private String roomCategoryId;
    private Long floorId;
    private Long status;
    private String bookingStatus;
    private String conditionStatus;
    private MultipartFile image;
    private String note;
}
