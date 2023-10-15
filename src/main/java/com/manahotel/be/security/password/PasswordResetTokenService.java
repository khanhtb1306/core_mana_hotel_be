package com.manahotel.be.security.password;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {
    @Autowired
    private final PasswordResetTokenRepository passwordResetTokenRepository;


    public void createPasswordResetTokenForUser(Staff staff, String passwordToken) {
        PasswordResetToken passwordRestToken = new PasswordResetToken(passwordToken, staff);
        passwordResetTokenRepository.save(passwordRestToken);
    }

    public String validatePasswordResetToken(String theToken){
        PasswordResetToken passwordToken = passwordResetTokenRepository.findByToken(theToken);
        if(passwordToken == null){
            return "Invalid password reset token";
        }
        Staff staff = passwordToken.getStaff();
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime()-calendar.getTime().getTime())<= 0){
            return "Link already expired, resend link";
        }
        return "valid";

    }
    public Optional<Staff> findStaffByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(passwordResetToken).getStaff());
    }

    public Optional<Staff> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(passwordResetToken).getStaff());
    }
}
