package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReportRoomCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReportRoomCapacityRepository extends JpaRepository<ReportRoomCapacity, Long> {
    @Query("SELECT r FROM ReportRoomCapacity r WHERE MONTH(r.createDate) = ?1 AND YEAR(r.createDate) = ?2")
    List<ReportRoomCapacity> findAllInCurrentMonth(Integer month, Integer year);

//    List<ReportRoomCapacity> findAllInLastMonth();

    @Query("SELECT YEAR(r.createDate) as reportYear, MONTH(r.createDate) as reportMonth, AVG(r.roomCapacityValue) as averageRoomCapacity " +
            "FROM ReportRoomCapacity r " +
            "WHERE YEAR(r.createDate) = ?1 " +
            "GROUP BY YEAR(r.createDate), MONTH(r.createDate)")
    List<Object[]> findAverageRoomCapacityByYear(Integer selectedYear);


    @Query("SELECT YEAR(r.createDate) as reportYear, AVG(r.roomCapacityValue) as averageRoomCapacity " +
            "FROM ReportRoomCapacity r " +
            "WHERE YEAR(r.createDate) BETWEEN ?1 AND YEAR(CURRENT_DATE) " +
            "GROUP BY YEAR(r.createDate)")
    List<Object[]> findRoomCapacityByYearRange(Integer startYear);


    @Query("SELECT DAYOFWEEK(r.createDate) as dayOfWeek, AVG(r.roomCapacityValue) as averageRoomCapacity " +
            "FROM ReportRoomCapacity r " +
            "WHERE (?1 IS NULL OR MONTH(r.createDate) = ?1) " +
            "AND YEAR(r.createDate) = ?2 " +
            "GROUP BY DAYOFWEEK(r.createDate) " +
            "ORDER BY DAYOFWEEK(r.createDate)")
    List<Object[]> findRoomCapacityWithDayOfWeekByMonth(Integer month, Integer year);




}
