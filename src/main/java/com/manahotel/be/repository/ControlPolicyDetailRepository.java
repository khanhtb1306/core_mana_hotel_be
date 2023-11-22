package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicy;
import com.manahotel.be.model.entity.ControlPolicyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ControlPolicyDetailRepository extends JpaRepository<ControlPolicyDetail, Long> {
    List<ControlPolicyDetail> findControlPolicyDetailByControlPolicy(ControlPolicy controlPolicy);
}
