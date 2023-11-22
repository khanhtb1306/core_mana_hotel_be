package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    List<Token> findByStaff(Staff staff);
}
