package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReportTopRoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTopRoomClassRepository extends JpaRepository<ReportTopRoomClass, Long> {
}
