package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.PolicyDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class PolicyDetailRequest {
    private List<PolicyDetailDTO> policyDetailDTO;
}
