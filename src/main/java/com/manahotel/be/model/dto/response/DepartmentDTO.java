package com.manahotel.be.model.dto.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentDTO {
    private String departmentId;
    private String departmentName;
    private String status;
}
