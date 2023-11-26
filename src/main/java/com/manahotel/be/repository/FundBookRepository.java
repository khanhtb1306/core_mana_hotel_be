package com.manahotel.be.repository;

import com.manahotel.be.model.entity.FundBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface FundBookRepository extends JpaRepository<FundBook, String> {
    FundBook findTopByFundBookIdContainingOrderByFundBookIdDesc(String fundBookId);

    @Query(value = "SELECT SUM(value) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('EXPENSE', 'OTHER_EXPENSE') " +
            "AND DATE(time) = DATE(?1)", nativeQuery = true)
    Float getAllExpenseByDay(Timestamp date);

    @Query(value = "SELECT SUM(value) FROM fund_book fb " +
            "WHERE fb.status = 'COMPLETE' AND fb.type IN ('INCOME', 'OTHER_INCOME') " +
            "AND DATE(time) = DATE(?1)", nativeQuery = true)
    Float getAllIncomeByDay(Timestamp date);
}
