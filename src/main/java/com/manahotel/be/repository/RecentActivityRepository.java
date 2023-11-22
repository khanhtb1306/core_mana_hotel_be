package com.manahotel.be.repository;

import com.manahotel.be.model.entity.RecentActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecentActivityRepository extends JpaRepository<RecentActivity, Long> {

    List<RecentActivity> findByCreateTimeGreaterThanEqualOrderByCreateTimeDesc(LocalDateTime startDate);
}
