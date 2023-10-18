package com.manahotel.be.security;

import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.StaffRepository;
import com.manahotel.be.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private StaffService staffService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Staff staff = new Staff();
        staff.setUsername(request.getUsername());
        staff.setPassword(bcryptEncoder.encode(request.getPassword()));
        staffRepository.save(staff);

        var jwtToken = jwtService.generateToken(staff);
        return AuthenticationResponse.builder().response(jwtToken).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Staff staff = staffService.findByuserName(request.getUsername());

//        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getPassword(),
                            request.getPassword()
                    )
            );
            var jwtToken = jwtService.generateToken(staff);
            return AuthenticationResponse.builder().response(jwtToken).build();
//        }
//        catch (Exception e){
//            return AuthenticationResponse.builder().response("Username or password is wrong").build();
//        }
    }

    public void ResetPassword(Staff staff, String newPassword) {
        staff.setPassword(bcryptEncoder.encode(newPassword));
        staffRepository.save(staff);

    }
}
