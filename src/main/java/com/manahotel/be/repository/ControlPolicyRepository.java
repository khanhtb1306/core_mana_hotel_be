package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ControlPolicyRepository extends JpaRepository<ControlPolicy, Long> {

    @Query("SELECT cp FROM ControlPolicy cp WHERE cp.reservationDetail.reservationDetailId = ?1 AND cp.policy.policyId = ?2")
    ControlPolicy findByReservationDetailIdAndPolicyId(Long reservationDetailId, String policyId);
}
