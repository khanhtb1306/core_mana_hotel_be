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
    public ResponseDTO getAllControlPolicyDetails() {
        return service.getAllControlPolicyDetails();
    }

    @GetMapping("/{id}")
    public ResponseDTO getControlPolicyDetailsById(@PathVariable String id) {
        return service.getControlPolicyDetailsById(id);
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
