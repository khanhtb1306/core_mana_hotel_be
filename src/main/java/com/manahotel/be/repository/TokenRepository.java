package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.security.password.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    List<Token> findByStaff(Staff staff);
}
