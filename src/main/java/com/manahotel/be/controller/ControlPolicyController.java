package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ControlPolicyDTO;
import com.manahotel.be.model.dto.ControlPolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ControlPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/control-policy")
public class ControlPolicyController {

    @Autowired
    private ControlPolicyService service;

    @GetMapping
    public ResponseDTO getAllControlPolicyWithControlPolicyDetails() {
        return service.getAllControlPolicyWithControlPolicyDetails();
    }

    @GetMapping("/{id}")
    public ResponseDTO getControlPolicyWithControlPolicyDetailsById(@PathVariable String id) {
        return service.getControlPolicyWithControlPolicyDetailsById(id);
    }

    @PostMapping
    public ResponseDTO createControlPolicy(ControlPolicyDTO controlPolicyDTO) {
        return service.createControlPolicy(controlPolicyDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateControlPolicy(@PathVariable String id, ControlPolicyDTO controlPolicyDTO) {
        return service.updateControlPolicy(id, controlPolicyDTO);
    }

    @PutMapping
    public ResponseDTO createUpdateControlPolicyDetail(List<ControlPolicyDetailDTO> listControlPolicyDetailDTOs) {
        return service.createUpdateControlPolicyDetail(listControlPolicyDetailDTOs);
    }
}
