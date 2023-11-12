package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ControlPolicyRepository extends JpaRepository<ControlPolicy, String> {
    ControlPolicy findTopByOrderByControlPolicyIdDesc();

    @Query("SELECT cp FROM ControlPolicy cp " +
            "LEFT JOIN ControlPolicyDetail cpd ON cp.controlPolicyId = cpd.controlPolicy.controlPolicyId")
    List<Object[]> findAllControlPolicyWithControlPolicyDetails();
}
