package com.manahotel.be.repository;

import com.manahotel.be.model.entity.PolicyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PolicyDetailRepository extends JpaRepository<PolicyDetail, Long> {

    @Query("select pd FROM PolicyDetail pd " +
            "where pd.policyId = ?1 and pd.status <> 6")
    List<PolicyDetail> getPolicyDetailByPolicyIdNotStatus6(String policyId);
}
