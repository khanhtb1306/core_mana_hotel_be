package com.manahotel.be.controller;

import com.manahotel.be.model.dto.DepartmentDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.StaffDTO;
import com.manahotel.be.model.entity.Department;
import com.manahotel.be.service.AuthenticationService;
import com.manahotel.be.service.DepartmentService;
import com.manahotel.be.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public ResponseDTO getAllStaff()
    {
        return staffService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseDTO getStaffDetail(@PathVariable Long id)
    {
        return staffService.getDetailsStaffById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteStaff(@PathVariable Long id)
    {
        return staffService.deleteStaff(id);
    }

    @PostMapping
    public ResponseDTO createAndUpdate(StaffDTO staffDTO){
        return staffService.createAndUpdateStaff(staffDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO changePassword(@PathVariable Long id,String oldPassword,String newPassword){
        return staffService.changePassword(id,oldPassword,newPassword);
    }
    @GetMapping("/department")
    public ResponseDTO getAllDepartment()
    {
        return departmentService.getAll();
    }

    @GetMapping("/department/{id}")
    public ResponseDTO getDepartmentDetail(@PathVariable String id)
    {
        return departmentService.getDetailsDepartmentById(id);
    }

    @PostMapping("/department")
    public ResponseDTO createAndUpdateDepartment(DepartmentDTO departmentDTO){
        return departmentService.createAndUpdateDepartment(departmentDTO);
    }

}