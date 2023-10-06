package com.manahotel.be.model.dto;

import lombok.Data;
import java.sql.Timestamp;
@Data
public class FloorDTO {
    private Long floorId;
    private String floorName;
    private Long status;
    private Long createdById;
    private Long updatedById;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
