package com.manahotel.be.service;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.TokenRepository;
import com.manahotel.be.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StaffService {
    @Autowired
    private StaffRepository repository;

    @Autowired
    private TokenService tokenService;

    public Staff findByuserName(String username) {
        Staff staff = repository.findByUsername(username);
        return staff;
    }

    public Optional<Staff> findByEmail(String email) {
        Optional<Staff> staff = repository.findByEmail(email);
        return staff;
    }

    public void createPasswordResetTokenForUser(Staff staff, String token) {

        tokenService.createTokenForUser(staff, token);
    }

    public String validatePasswordResetToken(String token) {
        return tokenService.validatePasswordResetToken(token);
    }

    public Staff findUserByPasswordToken(String passwordResetToken) {
        return tokenService.findStaffByPasswordToken(passwordResetToken).get();
    }




}
