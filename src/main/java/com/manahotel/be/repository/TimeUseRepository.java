package com.manahotel.be.repository;

import com.manahotel.be.model.entity.TimeUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeUseRepository extends JpaRepository<TimeUse, Long> {
    TimeUse findTopByOrderByTimeUseId();
}
