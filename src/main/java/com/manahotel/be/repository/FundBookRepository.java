package com.manahotel.be.repository;

import com.manahotel.be.model.entity.FundBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundBookRepository extends JpaRepository<FundBook, String> {
}
