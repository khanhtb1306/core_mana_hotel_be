package com.manahotel.be.model.dto.response;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class PolicyDTO {
    private String policyName;
    private String note;
    private Timestamp startTime;
    private Timestamp endTime;
    private Long status;
}
