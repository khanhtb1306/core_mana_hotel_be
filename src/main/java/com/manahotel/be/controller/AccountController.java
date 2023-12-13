package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.response.StaffDTO;
import com.manahotel.be.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private StaffService staffService;
    @GetMapping("/{id}")
    public ResponseDTO getAccountStaffDetail(@PathVariable Long id)
    {
        return staffService.getDetailsStaffById(id);
    }

    @PostMapping()
    public ResponseDTO createAndUpdateAccount(StaffDTO staffDTO){
        return staffService.createAndUpdateStaff(staffDTO);
    }
}
