package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class ControlPolicyDetailDTO {
    private Long controlPolicyDetailId;
    private Long reservationDetailId;
    private String controlPolicyId;
    private Long policyDetailId;
    private String typeValue;
    private Float value;
    private String discrepancy;
    private String note;
}
