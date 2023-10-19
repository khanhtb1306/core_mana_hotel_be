package com.manahotel.be.security.password;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Autowired
    private final TokenRepository tokenRepository;


    public void createTokenForUser(Staff staff, String passwordToken) {
        Token passwordRestToken = new Token(passwordToken, staff);
        tokenRepository.save(passwordRestToken);
    }
    public void revokeAllUserTokens(Staff staff) {
        var validUserTokens = tokenRepository.findByStaff(staff);
        if (validUserTokens.isEmpty())
            return;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0));
        calendar.add(Calendar.YEAR, 1);
        Date newDate = calendar.getTime();
        validUserTokens.forEach(token -> {
           token.setExpirationTime(newDate);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public String validatePasswordResetToken(String theToken){
        Token passwordToken = tokenRepository.findByToken(theToken);
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
        return Optional.ofNullable(tokenRepository.findByToken(passwordResetToken).getStaff());
    }

    public Optional<Staff> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(tokenRepository.findByToken(passwordResetToken).getStaff());
    }
}
