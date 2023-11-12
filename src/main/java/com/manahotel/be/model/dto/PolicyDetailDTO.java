package com.manahotel.be.model.dto;

import lombok.Data;

@Data
public class PolicyDetailDTO {
    private Long policyDetailId;
    private String policyId;
    private String roomCategoryId;
    private String customerGroupId;
    private String type;
    private String unit;
    private Long from;
    private Long to;
    private String typeValue;
    private String other;
    private String condition;
    private Float value;
    private String note;
    private Long status;
    private Boolean autoAddToInvoice;
}
