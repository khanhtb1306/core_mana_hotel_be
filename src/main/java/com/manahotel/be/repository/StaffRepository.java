package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    Staff findByuserName(String userName);
}
