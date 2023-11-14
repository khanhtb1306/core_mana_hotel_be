package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Size(max = 50)
    @Column(name = "department_id", nullable = false, length = 50)
    private String departmentId;

    @Size(max = 45)
    @Column(name = "department_name", length = 45)
    private String departmentName;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

}