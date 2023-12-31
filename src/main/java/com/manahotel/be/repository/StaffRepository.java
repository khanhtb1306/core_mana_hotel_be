package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByUsername(String username);
    List<Staff> findByPhoneNumber(String phoneNumber);
    List<Staff> findByIdentity(String Identity);
    Optional<Staff> findByEmail(String email);

     List<Staff> findByDepartment_DepartmentIdAndStatusIsNot(String departmentId, String status);


}
