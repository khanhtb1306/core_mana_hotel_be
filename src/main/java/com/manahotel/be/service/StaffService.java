package com.manahotel.be.service;

import com.manahotel.be.model.dto.StaffDTO;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StaffService {
    @Autowired
    private StaffRepository repository;

//    public void createStaff(StaffDTO dto) {
//        Staff staff = new Staff();
//        staff.setUsername(dto.getUsername());
//        staff.setPassword(dto.getPassword());
//        staff.setRoleId(dto.getRoleId());
//        staff.setStaffId(dto.getId());
//        repository.save(staff);
//    }
    public Staff findByuserName(String username){
        Staff staff = repository.findByUsername(username);
        return  staff;
    }
}
