package com.manahotel.be.service;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.PasswordResetTokenRepository;
import com.manahotel.be.repository.StaffRepository;
import com.manahotel.be.security.password.PasswordResetToken;
import com.manahotel.be.security.password.PasswordResetTokenService;
import com.manahotel.be.security.token.VerificationToken;
import com.manahotel.be.security.token.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StaffService {
    @Autowired
    private StaffRepository repository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;


    @Autowired
    private final VerificationTokenRepository tokenRepository;

//    @Autowired
//    private final PasswordEncoder passwordEncoder;

    public Staff findByuserName(String username) {
        Staff staff = repository.findByUsername(username);
        return staff;
    }

    public Optional<Staff> findByEmail(String email) {
        Optional<Staff> staff = repository.findByEmail(email);
        return staff;
    }

    public void createPasswordResetTokenForUser(Staff staff, String token) {

        passwordResetTokenService.createPasswordResetTokenForUser(staff, token);
    }

    public String validatePasswordResetToken(String token) {
        return passwordResetTokenService.validatePasswordResetToken(token);
    }

    public Staff findUserByPasswordToken(String passwordResetToken) {
        return passwordResetTokenService.findUserByPasswordToken(passwordResetToken).get();
    }

    public void saveUserVerificationToken(Staff staff, String token) {
        var verificationToken = new VerificationToken(token, staff);
        tokenRepository.save(verificationToken);
    }

//    public void resetStaffPassword(Staff staff, String newPassword) {
////        staff.setPassword(passwordEncoder.encode(newPassword));
//        repository.save(staff);
//    }

}
