package com.manahotel.be.controller;

import com.manahotel.be.model.dto.PolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.TimeUseDTO;
import com.manahotel.be.model.dto.request.PolicyDetailRequest;
import com.manahotel.be.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/{policyName}")
    public ResponseDTO getAllPolicyDetailByNamePolicy(@PathVariable String policyName) {
        return policyService.getAllPolicyDetailByNamePolicy(policyName);
    }

    @PostMapping
    public ResponseDTO createUpdatePolicyDetail(PolicyDetailRequest request) {
        return policyService.createUpdatePolicyDetail(request.getPolicyDetailDTO());
    }

    @GetMapping("/time_use")
    public ResponseDTO getSetupTimeUse() {
        return policyService.getSetupTimeUse();
    }

    @PutMapping("/time_use")
    public ResponseDTO createUpdateSetupTimeUse(TimeUseDTO timeUseDTO) {
        return policyService.createUpdateSetupTimeUse(timeUseDTO);
    }


}
