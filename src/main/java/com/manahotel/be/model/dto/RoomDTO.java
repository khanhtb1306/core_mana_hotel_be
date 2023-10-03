package com.manahotel.be.model.dto;

import java.sql.Timestamp;

public class RoomDTO {
    private String roomId;
    private String roomName;
    private String roomCategoryId;
    private String floor;
    private boolean status;
    private Long bookingStatus;
    private Long conditionStatus;
    private String note;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
