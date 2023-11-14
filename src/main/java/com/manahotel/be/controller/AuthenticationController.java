package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.security.*;
import com.manahotel.be.security.request.PasswordReset;
import com.manahotel.be.security.request.PasswordResetRequest;
import com.manahotel.be.security.RegistrationCompleteEventListener;
import com.manahotel.be.security.request.AuthenticationRequest;
import com.manahotel.be.security.request.RegisterRequest;
import com.manahotel.be.service.AuthenticationService;
import com.manahotel.be.service.StaffService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @Autowired
    private StaffService staffService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/password-reset-request")
    public ResponseEntity<String> resetPasswordRequest(@RequestBody PasswordResetRequest passwordRequestUtil,
                                       final HttpServletRequest servletRequest)
            throws UnsupportedEncodingException, MessagingException, jakarta.mail.MessagingException {

        Optional<Staff> staff = staffService.findByEmail(passwordRequestUtil.getEmail());
        String passwordResetUrl = "";
        String passwordResetToken="";
        if (staff.isPresent()) {
            passwordResetToken = UUID.randomUUID().toString();
            staffService.createPasswordResetTokenForUser(staff.get(), passwordResetToken);
            passwordResetUrl = service.passwordResetEmailLink(staff.get(), service.applicationUrl(servletRequest), passwordResetToken,passwordRequestUtil.getType());
        }
        else{
            return new ResponseEntity<>("Không tìm thấy email!", HttpStatus.OK);
        }
        log.info(passwordResetUrl);
        return new ResponseEntity<String>(passwordResetToken, HttpStatus.OK);
    }


    @PostMapping("/reset-password")
    public  ResponseEntity<String>  resetPassword(@RequestBody PasswordReset passwordResetRequest,
                                @RequestParam("token") String passwordResetToken) {
        String tokenValidationResult = staffService.validatePasswordResetToken(passwordResetToken);
        if (!tokenValidationResult.equalsIgnoreCase("valid")) {
            return new ResponseEntity<>("Mã thay đổi không hợp lệ", HttpStatus.OK);
        }

        Staff staff = staffService.findUserByPasswordToken(passwordResetToken);
        if (staff != null) {
            if(passwordResetRequest.getType() == 1)
            {
                ResponseDTO responseDTO= staffService.changePassword(staff.getStaffId(), passwordResetRequest.getOldPassword(), passwordResetRequest.getNewPassword());
                if (responseDTO.isSuccess())
                {
                    return new ResponseEntity<>(responseDTO.getDisplayMessage(), HttpStatus.OK);
                }
                return new ResponseEntity<>(responseDTO.getDisplayMessage(),  HttpStatus.OK);
            }
            service.ResetPassword(staff, passwordResetRequest.getNewPassword());
            return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
        }
        return new ResponseEntity<>("Mã thay đổi không hợp lệ", HttpStatus.OK);

    }


}
