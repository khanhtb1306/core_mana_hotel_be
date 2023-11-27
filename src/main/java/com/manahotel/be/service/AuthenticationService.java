package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.StaffRepository;
import com.manahotel.be.security.*;
import com.manahotel.be.security.request.AuthenticationRequest;
import com.manahotel.be.security.request.PasswordResetRequest;
import com.manahotel.be.security.request.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import static com.manahotel.be.common.constant.Role.ROLE_MANAGER;
import static com.manahotel.be.common.constant.Role.ROLE_RECEPTIONIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RegistrationCompleteEventListener eventListener;

    @Autowired
    private final StaffRepository staffRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private StaffService staffService;

    public AuthenticationResponse register(RegisterRequest request) {
        Staff staff = new Staff();
        staff.setUsername(request.getUsername());
        staff.setPassword(bcryptEncoder.encode(request.getPassword()));
        staff.setRole(ROLE_MANAGER);
        staffRepository.save(staff);

        var jwtToken = jwtService.generateToken(staff);
        return AuthenticationResponse.builder().response(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Staff staff = staffService.findByuserName(request.getUsername());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(staff);
            jwtService.revokeAllUserTokens(staff);
            jwtService.createTokenForUser(staff, jwtToken);

            log.info("Login successful");
            return AuthenticationResponse.builder().response(jwtToken).build();
        } catch (Exception e) {
            if (staff == null) {
                log.error("Username is wrong");
            } else {
                log.error("Password is wrong");
            }
            return AuthenticationResponse.builder().response("Tên người dùng hoặc mật khẩu sai").build();
        }
    }

    public void ResetPassword(Staff staff, String newPassword) {
        staff.setPassword(bcryptEncoder.encode(newPassword));
        staffRepository.save(staff);

    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://localhost:3000";
    }

    public String passwordResetEmailLink(Staff staff, String applicationUrl, String passwordResetToken, PasswordResetRequest passwordResetRequest) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String url = "";
        if (passwordResetRequest.getType() == 1) {
            url = applicationUrl + "/resetPassword?" + passwordResetToken;

            String password = generateRandomPassword(6);
            staff.setPassword(staffService.passwordCoder(password));
            staffRepository.save(staff);
            eventListener.sendVerificationEmail(url, staff,password);
        } else {
            url = applicationUrl + "/resetPassword?" + passwordResetToken;
            eventListener.sendPasswordResetVerificationEmail(url, staff);
        }
        log.info("Click the link to reset your password : ", url);
        return url;
    }
    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder password = new StringBuilder();

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }


}
