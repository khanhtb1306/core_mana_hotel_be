package com.manahotel.be.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
@Data
public class RoomDTO {
    private String roomName;
    private String roomCategoryId;
    private Long floorId;
    private Long status;
    private Long bookingStatus;
    private Long conditionStatus;
    private MultipartFile image;
    private String note;
}
