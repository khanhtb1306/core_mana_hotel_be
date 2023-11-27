package com.manahotel.be.service;

import com.manahotel.be.common.constant.Const;
import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.StaffDTO;
import com.manahotel.be.model.entity.CustomerGroup;
import com.manahotel.be.model.entity.Department;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.DepartmentRepository;
import com.manahotel.be.repository.StaffRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

import static com.manahotel.be.common.constant.Role.ROLE_MANAGER;
import static com.manahotel.be.common.constant.Role.ROLE_RECEPTIONIST;

@Slf4j
@RequiredArgsConstructor
@Service
public class StaffService {
    @Autowired
    private StaffRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private DepartmentRepository departmentRepository;


    public ResponseDTO getAll() {
        log.info("----- Get All Staff Start------");
        try {
            List<Staff> staffs = repository.findAll();
            log.info("----- Get All Staff End ------");
            return ResponseUtils.success(staffs, "Lấy tất cả thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lấy tất cả thất bại");
        }
    }

    public ResponseDTO createAndUpdateStaff(StaffDTO staffDTO) {
        log.info("----- Create And Update Staff Start------");
        try {
            Staff staff = new Staff();
            if (staffDTO.getStaffId() != null) {
                staff = getStaffById(staffDTO.getStaffId());
            }
            if (findByEmail(staffDTO.getEmail()).isPresent() && !staffDTO.getEmail().equals(staff.getEmail()))
            {
                log.info("----- Create And Update Staff End ------");
                return ResponseUtils.error("Email đã tồn tại");

            }
            if(staffDTO.getUsername() != null)
            {
                if (findByuserName(staffDTO.getUsername()) != null  && !staffDTO.getUsername().equals(staff.getUsername()))
                {
                    log.info("----- Create And Update Staff End ------");
                    return ResponseUtils.error("Username đã tồn tại");
                }
            }
            if (!validateDob(staffDTO.getDob())) {

                log.info("----- Create And Update Staff End ------");
                return ResponseUtils.error("Ngày sinh nhỏ hơn ngày hiện tại!");
            }
            if (repository.findByIdentity(staffDTO.getIdentity()) != null) {
               if(staff != null && !staff.getIdentity().equals(staffDTO.getIdentity()))
               {
                   log.info("----- Create And Update Staff End ------");
                   return ResponseUtils.error("Số chứng minh thư đã tồn tại!");
               }
            }

            commonMapping(staff, staffDTO);
            repository.save(staff);

            log.info("----- Create And Update Staff End success------");
            return ResponseUtils.success(staff.getStaffId(), "Lưu thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lưu thất bại");
        }
    }
    public boolean validateDob(String dob) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(dob);
            Date currentDate = new Date();
            return parsedDate.before(currentDate);
        } catch (ParseException e) {
            return false;
        }
    }
    public ResponseDTO adminStaff(Long staffId) {
        log.info("----- Admin Staff Start------");
        try {
            Staff staff = getStaffById(staffId);
            staff.setRole(ROLE_MANAGER);
            repository.save(staff);
            log.info("----- Admin Staff End ------");
            return ResponseUtils.success(staffId, "Chuyển admin thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Chuyển admin thất bại");
        }
    }
    public ResponseDTO deleteStaff(Long staffId) {
        log.info("----- Delete Staff Start------");
        try {
            Staff staff = getStaffById(staffId);
            staff.setStatus(Status.NO_ACTIVE);
            repository.save(staff);
            log.info("----- Delete Staff End ------");
            return ResponseUtils.success(staffId, "Xóa thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Xóa thất bại");
        }
    }

    public ResponseDTO deleteStaffByList(List<Long> idList) {
        log.info("----- Delete List Staff Start------");
        Map<Object, String> result = new HashMap<>();

        try {
            if (idList == null || idList.isEmpty()) {
                return ResponseUtils.error("Danh sách ID không hợp lệ.");
            }
            for (Long id : idList) {
                ResponseDTO responseDTO = deleteStaff(id);
                result.put(responseDTO.getResult(), responseDTO.getDisplayMessage());
            }
            log.info("----- Delete List Staff End ------");
            return ResponseUtils.success(result, "Xóa thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Xóa thất bại");
        }
    }

    public ResponseDTO getDetailsStaffById(Long staffId) {
        log.info("----- Get Staff Start------");
        try {
            Staff staff = getStaffById(staffId);
            log.info("----- Get Staff End ------");
            return ResponseUtils.success(staff, "Lấy thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lấy thất bại");
        }
    }

    private void commonMapping(Staff staff, StaffDTO staffDTO) throws IOException {
        staff.setStaffId(staffDTO.getStaffId() != null ? staffDTO.getStaffId() : staff.getStaffId());
        staff.setStaffName(staffDTO.getStaffName() != null ? staffDTO.getStaffName() : staff.getStaffName());
        staff.setUsername(staffDTO.getUsername() != null ? staffDTO.getUsername() : staff.getUsername());
//        staff.setPassword(staffDTO.getPassword() != null ? passwordCoder(staffDTO.getPassword()) : staff.getPassword());
        staff.setStatus(Status.ACTIVE);
        staff.setRole(staffDTO.getRole() != null ? staffDTO.getRole() : staff.getRole());
        Department department = null;
        if(staffDTO.getDepartmentId() == null && staff.getDepartment() != null){
             department = staff.getDepartment();
        }
        else{
             department = departmentRepository.findById(staffDTO.getDepartmentId()).orElseThrow(() -> new IllegalStateException("Department not found"));
        }
        staff.setDepartment(department);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(staffDTO.getDob());
            Timestamp dobTimestamp = new Timestamp(parsedDate.getTime());
            staff.setDob(dobTimestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        staff.setAddress(staffDTO.getAddress() != null ? staffDTO.getAddress() : staff.getAddress());
        staff.setEmail(staffDTO.getEmail() != null ? staffDTO.getEmail() : staff.getEmail());
        staff.setGender(staffDTO.isGender());
        staff.setIdentity(staffDTO.getIdentity() != null ? staffDTO.getIdentity() : staff.getIdentity());
        staff.setTaxCode(staffDTO.getTaxCode() != null ? staffDTO.getTaxCode() : staff.getTaxCode());
        staff.setPhoneNumber(staffDTO.getPhoneNumber() != null ? staffDTO.getPhoneNumber() : staff.getPhoneNumber());
        staff.setImage(staffDTO.getImage() != null ? staffDTO.getImage().getBytes() : staff.getImage());

    }

    public ResponseDTO changePassword(Long staffId, String oldPassword, String newPassword) {
        log.info("----- Change Password Start------");
        try {
            Staff staff = getStaffById(staffId);
            if (bcryptEncoder.matches(oldPassword, staff.getPassword())) {
                staff.setPassword(passwordCoder(newPassword));
                repository.save(staff);
                log.info("----- Change Password End ------");
                return ResponseUtils.success("Thay đổi mật khẩu thành công");
            }

            log.info("----- Change Password Error ------");
            return ResponseUtils.error("Thay đổi mật khẩu thất bại");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Thay đổi mật khẩu thất bại");
        }
    }

    public String passwordCoder(String password) {
        return bcryptEncoder.encode(password);
    }

    public Staff getStaffById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));

    }

    public Staff findByuserName(String username) {
        Staff staff = repository.findByUsername(username);
        return staff;
    }

    public Optional<Staff> findByEmail(String email) {
        Optional<Staff> staff = repository.findByEmail(email);
        return staff;
    }

    public void createPasswordResetTokenForUser(Staff staff, String token) {
        jwtService.createTokenForUser(staff, token);
    }

    public String validatePasswordResetToken(String token) {
        return jwtService.validatePasswordResetToken(token);
    }

    public Staff findUserByPasswordToken(String passwordResetToken) {
        return jwtService.findStaffByPasswordToken(passwordResetToken).get();
    }


}
