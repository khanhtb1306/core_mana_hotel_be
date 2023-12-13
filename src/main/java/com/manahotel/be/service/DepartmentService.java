package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.DepartmentDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Department;
import com.manahotel.be.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    public ResponseDTO getAll() {
        log.info("----- Get All Department Start------");
        try {
            List<Department> departments = repository.findAll();
            log.info("----- Get All Department End ------");
            return ResponseUtils.success(departments,"Lấy tất cả thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lấy tất cả thất bại");
        }
    }

    public ResponseDTO createAndUpdateDepartment(DepartmentDTO departmentDTO){
        log.info("----- Create And Update Department Start------");
        try {
            Department department = new Department();
            Department latestDepartment = repository.findTopByOrderByDepartmentIdDesc();
            String latestId = (latestDepartment == null) ? null : latestDepartment.getDepartmentId();
            String nextId = IdGenerator.generateId(latestId, "PB");
            if (departmentDTO.getDepartmentId() != null) {
                department = getDepartmentById(departmentDTO.getDepartmentId());
            }
            else{
                departmentDTO.setDepartmentId(nextId);
            }
            commonMapping(department, departmentDTO);
            repository.save(department);

            log.info("----- Create And Update Department End ------");
            return ResponseUtils.success(department.getDepartmentId(),"Lưu thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lưu thất bại");
        }
    }

    private void commonMapping(Department department, DepartmentDTO departmentDTO) {
        department.setDepartmentId(departmentDTO.getDepartmentId() != null ? departmentDTO.getDepartmentId() : department.getDepartmentId());
        department.setDepartmentName(departmentDTO.getDepartmentName() != null ? departmentDTO.getDepartmentName() : department.getDepartmentName());
        department.setStatus(departmentDTO.getStatus() != null ? departmentDTO.getStatus() : department.getStatus());
    }


    public ResponseDTO getDetailsDepartmentById(String departmentId) {
        log.info("----- Get Department Start------");
        try {
            Department department = getDepartmentById(departmentId);
            log.info("----- Get Department End ------");
            return ResponseUtils.success(department,"Lấy thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lấy thất bại");
        }
    }
    public Department getDepartmentById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));

    }
}
