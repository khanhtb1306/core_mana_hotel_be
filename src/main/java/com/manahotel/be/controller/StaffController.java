package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.DepartmentDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.response.StaffDTO;
import com.manahotel.be.service.DepartmentService;
import com.manahotel.be.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseDTO deleteStaff(@PathVariable List<Long> id)
    {
        return staffService.deleteStaffByList(id);
    }

    @PutMapping("/admin/{id}")
    public ResponseDTO adminStaff(@PathVariable Long id)
    {
        return staffService.adminStaff(id);
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
    @DeleteMapping("/department/{id}")
    public ResponseDTO deleteDepartment(@PathVariable String id) {
        return departmentService.deleteDepartmentById(id);
    }
}
