package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReportTopRoomClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReportTopRoomClassRepository extends JpaRepository<ReportTopRoomClass, Long> {

    @Query("SELECT r FROM ReportTopRoomClass r WHERE r.roomClassId = ?1 AND MONTH(r.createDate) = MONTH(?2) AND YEAR(r.createDate) = YEAR(?2)")
    ReportTopRoomClass findByRoomClassIdAndCreateDate(String roomClassId, Timestamp createDate);


    @Query("SELECT r.roomClassId, SUM(r.numberOfRental) AS totalNumberOfRental, SUM(r.revenue) AS totalRevenue " +
            "FROM ReportTopRoomClass r " +
            "WHERE MONTH(r.createDate) = ?1 AND YEAR(r.createDate) = ?2 " +
            "GROUP BY r.roomClassId")
    List<Object[]> getTotalRentalAndRevenueByMonth(Integer month, Integer year);



}
