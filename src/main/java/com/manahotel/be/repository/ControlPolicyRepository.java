package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicy;
import com.manahotel.be.model.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ControlPolicyRepository extends JpaRepository<ControlPolicy, Long> {
}
