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
        staff.setRoleId(request.getRoleId());
        staffRepository.save(staff);

//        StaffDTO staffDTO = new StaffDTO();
//        staffDTO.setId(staff.getStaffId());
//
//        staffDTO.setUsername(staff.getUsername());
//        staffDTO.setPassword(staff.getPassword());
//        staffDTO.setRoleId(staff.getRoleId());

        var jwtToken = jwtService.generateToken(staff);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Staff staff = staffService.findByuserName(request.getUsername());


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPassword(),
                        request.getPassword()
                )
        );

//        StaffDTO staffDTO = new StaffDTO();
//        staffDTO.setId(staff.getStaffId());
//
//        staffDTO.setUsername(staff.getUsername());
//        staffDTO.setPassword(staff.getPassword());
//        staffDTO.setRoleId(staff.getRoleId());
        var jwtToken = jwtService.generateToken(staff);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
