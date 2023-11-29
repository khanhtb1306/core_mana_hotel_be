package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ControlPolicyDetailRepository extends JpaRepository<ControlPolicyDetail, Long> {
}
