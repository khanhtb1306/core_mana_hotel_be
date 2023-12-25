package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.DepartmentDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Department;
import com.manahotel.be.repository.DepartmentRepository;
import com.manahotel.be.repository.StaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        Mockito.reset(departmentRepository);
    }

    @Test
    void createAndUpdateDepartment() {
        // Mock data
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentName("IT");

        // Mock repository behavior
        when(departmentRepository.findTopByOrderByDepartmentIdDesc()).thenReturn(new Department());
        when(departmentRepository.save(any(Department.class))).thenReturn(new Department());

        // Call the service method
        ResponseDTO responseDTO = departmentService.createAndUpdateDepartment(departmentDTO);

        // Verify the results
        assertEquals("Lưu thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteDepartmentById() {
        // Mock data
        String departmentId = "1";

        // Mock repository behavior for departmentRepository
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(new Department()));

        // Mock repository behavior for staffRepository
        StaffRepository staffRepository = mock(StaffRepository.class);
        when(staffRepository.findByDepartment_DepartmentIdAndStatusIsNot(eq(departmentId), anyString()))
                .thenReturn(Collections.emptyList()); // Assuming you want to mock an empty list for the test

        // Create an instance of the service and set the mocked repositories
        DepartmentService departmentService = new DepartmentService(departmentRepository, staffRepository);

        // Call the service method
        ResponseDTO responseDTO = departmentService.deleteDepartmentById(departmentId);

        // Verify the results
        assertEquals("Xóa thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteDepartmentByIdNotFound() {
        // Mock data
        String departmentId = "1";

        // Mock repository behavior for not found case
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        // Call the service method
        ResponseDTO responseDTO = departmentService.deleteDepartmentById(departmentId);

        // Verify the results
        assertEquals("Xóa thất bại", responseDTO.getDisplayMessage());
    }
}