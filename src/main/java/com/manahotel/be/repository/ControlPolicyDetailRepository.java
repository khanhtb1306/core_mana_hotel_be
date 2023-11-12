package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicy;
import com.manahotel.be.model.entity.ControlPolicyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ControlPolicyDetailRepository extends JpaRepository<ControlPolicyDetail, Long> {
    List<ControlPolicyDetail> findControlPolicyDetailByControlPolicy(ControlPolicy controlPolicy);
}
