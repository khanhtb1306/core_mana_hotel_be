package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {
    Department findTopByOrderByDepartmentIdDesc();
}
