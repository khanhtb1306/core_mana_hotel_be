package com.manahotel.be.service;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.StaffRepository;
import com.manahotel.be.security.*;
import com.manahotel.be.security.request.AuthenticationRequest;
import com.manahotel.be.security.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.manahotel.be.common.constant.Role.ROLE_RECEPTIONIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private StaffService staffService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Staff staff = new Staff();
        staff.setUsername(request.getUsername());
        staff.setPassword(bcryptEncoder.encode(request.getPassword()));
        staff.setRole(ROLE_RECEPTIONIST);
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
            tokenService.revokeAllUserTokens(staff);
            tokenService.createTokenForUser(staff, jwtToken);

            log.info("Login successful");
            return AuthenticationResponse.builder().response(jwtToken).build();
        } catch (Exception e) {
            if (staff == null) {
                log.error("Username is wrong");
            } else {
                log.error("Password is wrong");
            }
            return AuthenticationResponse.builder().response("Username or password is wrong").build();
        }
    }

    public void ResetPassword(Staff staff, String newPassword) {
        staff.setPassword(bcryptEncoder.encode(newPassword));
        staffRepository.save(staff);

    }
}
