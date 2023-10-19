package com.manahotel.be.controller;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.security.AuthenticationRequest;
import com.manahotel.be.security.AuthenticationResponse;
import com.manahotel.be.security.AuthenticationService;
import com.manahotel.be.security.RegisterRequest;
import com.manahotel.be.security.password.PasswordResetRequest;
import com.manahotel.be.security.password.RegistrationCompleteEventListener;
import com.manahotel.be.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final RegistrationCompleteEventListener eventListener;
    @Autowired
    private AuthenticationService service;

    @Autowired
    private StaffService staffService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestBody PasswordResetRequest passwordRequestUtil,
                                       final HttpServletRequest servletRequest)
            throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {

        Optional<Staff> staff = staffService.findByEmail(passwordRequestUtil.getEmail());
        String passwordResetUrl = "";
        if (staff.isPresent()) {
            String passwordResetToken = UUID.randomUUID().toString();
            staffService.createPasswordResetTokenForUser(staff.get(), passwordResetToken);
            passwordResetUrl = passwordResetEmailLink(staff.get(), applicationUrl(servletRequest), passwordResetToken);
        }
        log.info(passwordResetUrl);
        return passwordResetUrl;
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();
    }

    private String passwordResetEmailLink(Staff staff, String applicationUrl, String passwordResetToken) throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {
        String url = applicationUrl + "/api/v1/auth/reset-password?token=" + passwordResetToken;
        eventListener.sendPasswordResetVerificationEmail(url, staff);
        log.info("Click the link to reset your password :  {}", url);
        return url;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody PasswordResetRequest passwordResetRequest,
                                @RequestParam("token") String passwordResetToken) {
        String tokenValidationResult = staffService.validatePasswordResetToken(passwordResetToken);
        if (!tokenValidationResult.equalsIgnoreCase("valid")) {
            return "invalid reset token";
        }

        Staff staff = staffService.findUserByPasswordToken(passwordResetToken);
        if (staff != null) {
            service.ResetPassword(staff, passwordResetRequest.getNewPassword());
            return "Password has been reset successful";
        }
        return "invalid password reset token";

    }


}
