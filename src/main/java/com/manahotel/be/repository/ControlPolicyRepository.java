package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ControlPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ControlPolicyRepository extends JpaRepository<ControlPolicy, Long> {
    @Query("SELECT cp FROM ControlPolicy cp WHERE cp.reservationDetail.reservationDetailId = ?1 AND cp.policy.policyId = ?2 AND cp.status <> false")
    ControlPolicy findByReservationDetailIdAndPolicyId(Long reservationDetailId, String policyId);

    @Query("SELECT cp FROM ControlPolicy cp WHERE cp.reservationDetail.reservationDetailId = ?1 AND cp.status = true ")
    List<ControlPolicy> findControlPolicyByReservationDetail_ReservationDetailId(Long reservationDetailId);
}
