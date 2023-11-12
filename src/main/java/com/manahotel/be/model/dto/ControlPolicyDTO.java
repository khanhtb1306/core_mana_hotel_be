package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class ControlPolicyDTO {
    private String controlPolicyId;
    private String reservationId;
    private String policyId;
    private Float totalPrice;
    private String note;
}
