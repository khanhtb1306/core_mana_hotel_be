package com.manahotel.be.model.dto;

import java.sql.Timestamp;

public class RoomCategoryDTO {
    private String roomCategoryId;
    private String roomCategoryName;
    private Long numOfRooms;
    private boolean status;
    private String description;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
