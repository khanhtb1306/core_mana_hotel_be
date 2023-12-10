package com.manahotel.be.repository;

import com.manahotel.be.model.entity.FundBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FundBookRepository extends JpaRepository<FundBook, String> {
    FundBook findTopByFundBookIdContainingOrderByFundBookIdDesc(String fundBookId);
    List<FundBook> findTop10ByStaffOrderByTimeDesc(String staff);

    @Query(value = "SELECT SUM(value) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('EXPENSE', 'OTHER_EXPENSE') " +
            "AND DATE(time) = DATE(?1)", nativeQuery = true)
    Float getAllExpenseByDay(Timestamp date);

    @Query(value = "SELECT SUM(value) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('INCOME', 'OTHER_INCOME') " +
            "AND DATE(time) = DATE(?1)", nativeQuery = true)
    Float getAllIncomeByDay(Timestamp date);

    List<FundBook> findByFundBookIdContaining(String keyword);

    @Query(value = "SELECT COALESCE(SUM(value), 0) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('EXPENSE', 'OTHER_EXPENSE') " +
            "AND MONTH(time) = ?1 AND YEAR(time) = ?2", nativeQuery = true)
    Float getAllExpenseByMonth(Integer month, Integer year);

    @Query(value = "SELECT COALESCE(SUM(value), 0) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('INCOME', 'OTHER_INCOME') " +
            "AND MONTH(time) = ?1 AND YEAR(time) = ?2", nativeQuery = true)
    Float getAllIncomeByMonth(Integer month, Integer year);

    @Query(value = "SELECT COALESCE(SUM(value), 0) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('EXPENSE', 'OTHER_EXPENSE') " +
            "AND YEAR(time) = ?1", nativeQuery = true)
    Float getAllExpenseByYear(Integer year);

    @Query(value = "SELECT COALESCE(SUM(value), 0) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('INCOME', 'OTHER_INCOME') " +
            "AND YEAR(time) = ?1", nativeQuery = true)
    Float getAllIncomeByYear(Integer year);

    @Query("SELECT fb from FundBook fb " +
            "WHERE MONTH(time) = ?1 AND YEAR(time) = ?2")
    List<FundBook> getAllFundBookByMonth(Integer month, Integer year);

    @Query("SELECT fb from FundBook fb " +
            "WHERE YEAR(time) = ?1")
    List<FundBook> getAllFundBookByYear(Integer year);
}
