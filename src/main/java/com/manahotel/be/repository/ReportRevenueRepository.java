package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReportRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRevenueRepository extends JpaRepository<ReportRevenue, Long> {
    @Query("SELECT rr FROM ReportRevenue rr WHERE MONTH(rr.createdDate) = ?1 AND YEAR(rr.createdDate) = ?2 " +
            "ORDER BY rr.createdDate ASC")
    List<ReportRevenue> findAllByMonth(Integer month, Integer year);


    @Query("SELECT rr FROM ReportRevenue rr WHERE YEAR(rr.createdDate) = ?1 " +
            "ORDER BY rr.createdDate ASC")
    List<ReportRevenue> findAllByYear(Integer year);

    @Query("SELECT rr FROM ReportRevenue rr WHERE YEAR(rr.createdDate) BETWEEN ?1 AND YEAR(CURRENT_DATE) " +
            "ORDER BY rr.createdDate ASC")
    List<ReportRevenue> findAllByYearRange(Integer year);
}
