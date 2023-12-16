package com.manahotel.be.model.dto.response;

import lombok.Data;

@Data
public class PolicyDetailDTO {
    private Long policyDetailId;
    private String policyId;
    private String roomCategoryId;
    private String type;
    private String unit;
    private Long limitValue;
    private String typeValue;
    private Float policyValue;
    private String note;
    private Long status;
    private Boolean autoAddToInvoice;
}
