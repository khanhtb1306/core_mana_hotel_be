package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.response.StaffDTO;
import com.manahotel.be.model.entity.Department;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.DepartmentRepository;
import com.manahotel.be.repository.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StaffServiceTest {

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder bcryptEncoder;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    void setUp() {
        Mockito.reset(staffRepository, jwtService, bcryptEncoder, departmentRepository);
    }

    @Test
    void createAndUpdateStaff() {
        // Mock data
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStaffName("John Doe");
        staffDTO.setEmail("john.doe@example.com");
        staffDTO.setDob("1970-01-02 08:00:00");
        staffDTO.setIdentity("071000965");
        staffDTO.setPhoneNumber("0988886753");
        staffDTO.setUsername("johndoe");

        // Mock repository behavior
        when(staffRepository.findById(1L)).thenReturn(Optional.of(new Staff()));
        when(staffRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.empty());
        when(staffRepository.save(any(Staff.class))).thenReturn(new Staff());

        // Mock Department
        Department department = new Department();
        department.setDepartmentId("your_department_id");
        when(departmentRepository.findById(any())).thenReturn(Optional.of(department));
        staffDTO.setDepartmentId(department.getDepartmentId());  // Set the department ID in the DTO

        // Call the service method
        ResponseDTO responseDTO = staffService.createAndUpdateStaff(staffDTO);

        // Verify the results
        assertEquals("Lưu thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteStaff() {
        // Mock data
        Long staffId = 1L;

        // Mock repository behavior
        when(staffRepository.findById(1L)).thenReturn(Optional.of(new Staff()));

        // Call the service method
        ResponseDTO responseDTO = staffService.deleteStaff(staffId);

        // Verify the results
        assertEquals("Dừng hoạt động nhân viên thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteStaffNotFound() {
        // Mock data
        Long staffId = 1L;

        // Mock repository behavior for not found case
        when(staffRepository.findById(staffId)).thenReturn(null);

        // Call the service method
        ResponseDTO responseDTO = staffService.deleteStaff(staffId);

        // Verify the results
        assertEquals("Dừng hoạt động nhân viên thất bại", responseDTO.getDisplayMessage());
    }

    // Add more test cases as needed
}