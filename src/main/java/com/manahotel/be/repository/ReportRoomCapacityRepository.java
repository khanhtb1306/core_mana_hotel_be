package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReportRoomCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRoomCapacityRepository extends JpaRepository<ReportRoomCapacity, Long> {
    @Query("SELECT r FROM ReportRoomCapacity r WHERE MONTH(r.createDate) = MONTH(CURRENT_DATE()) AND YEAR(r.createDate) = YEAR(CURRENT_DATE())")
    List<ReportRoomCapacity> findAllInCurrentMonth();
    @Query("SELECT r FROM ReportRoomCapacity r WHERE MONTH(r.createDate) = MONTH(CURRENT_DATE()) - 1 AND YEAR(r.createDate) = YEAR(CURRENT_DATE())")
    List<ReportRoomCapacity> findAllInLastMonth();
}
