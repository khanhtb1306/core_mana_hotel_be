package com.manahotel.be.model.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class RoomDTO {
    private String roomId;
    private String roomName;
    private String roomCategoryId;
    private String floorId;
    private boolean status;
    private Long bookingStatus;
    private Long conditionStatus;
    private String note;
    private Long createdById;
    private Long updatedById;
    private String createdDate;
    private String updatedDate;
}
