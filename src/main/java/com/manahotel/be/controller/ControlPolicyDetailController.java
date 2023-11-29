package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ControlPolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ControlPolicyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/control-policy-detail")
public class ControlPolicyDetailController {

    @Autowired
    private ControlPolicyDetailService service;

    @GetMapping
    public ResponseDTO getAllControlPolicyWithControlPolicyDetails() {
        return service.getAllControlPolicyWithControlPolicyDetails();
    }

    @GetMapping("/{id}")
    public ResponseDTO getControlPolicyWithControlPolicyDetailsById(@PathVariable String id) {
        return service.getControlPolicyWithControlPolicyDetailsById(id);
    }

    @PostMapping
    public ResponseDTO createControlPolicy(ControlPolicyDetailDTO controlPolicyDetailDTO) {
        return service.createControlPolicy(controlPolicyDetailDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateControlPolicy(@PathVariable Long id, ControlPolicyDetailDTO controlPolicyDetailDTO) {
        return service.updateControlPolicy(id, controlPolicyDetailDTO);
    }

}
