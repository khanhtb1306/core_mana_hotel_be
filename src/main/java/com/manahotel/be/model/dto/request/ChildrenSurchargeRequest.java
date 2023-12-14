package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.CustomerDTO;
import lombok.Data;

import java.util.List;

@Data
public class ChildrenSurchargeRequest {
    private long reservationDetailId;
    private String roomCategoryId;
    private float roomPrice;
    private List<CustomerDTO> customerDTOS;
    private long timeUse;
    private boolean status;
}
