package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.model.dto.response.CustomerDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.CustomerGroup;
import com.manahotel.be.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerGroupService customerGroupService;

    public List<Customer> getAll() {

        List<Customer> allCustomers = customerRepository.findAll();

        if (!allCustomers.isEmpty()) {
            allCustomers.remove(0);
        }

        return allCustomers;
    }

    private void commonMapping(Customer customer, CustomerDTO customerDTO) throws IOException {
        customer.setCustomerName((customerDTO.getCustomerName() != null && !customerDTO.getCustomerName().isEmpty()) ? customerDTO.getCustomerName() : customer.getCustomerName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber() != null ? customerDTO.getPhoneNumber() : customer.getPhoneNumber());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(customerDTO.getDob());
            Timestamp dobTimestamp = new Timestamp(parsedDate.getTime());
            customer.setDob(dobTimestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        customer.setStatus(Status.ACTIVE);
        customer.setEmail(customerDTO.getEmail() != null ? customerDTO.getEmail() : customer.getEmail());
        customer.setAddress(customerDTO.getAddress() != null ? customerDTO.getAddress() : customer.getAddress());
        customer.setIdentity(customerDTO.getIdentity() != null ? customerDTO.getIdentity() : customer.getIdentity());
        customer.setNationality(customerDTO.getNationality() != null ? customerDTO.getNationality() : customer.getNationality());
        customer.setTaxCode(customerDTO.getTaxCode() != null ? customerDTO.getTaxCode() : customer.getTaxCode());
        customer.setGender(customerDTO.isGender());
        customer.setImage(customerDTO.getImage() != null ? customerDTO.getImage().getBytes() : customer.getImage());
        customer.setIsCustomer(customerDTO.isCustomer());
    }

    public ResponseEntity<Map<String, String>> create(CustomerDTO customerDTO) throws IOException {
        log.info("----- Add Customer Start -----");
        try {
            Customer latestCustomer = customerRepository.findTopByOrderByCustomerIdDesc();
            String latestId = (latestCustomer == null) ? null : latestCustomer.getCustomerId();
            String nextId = IdGenerator.generateId(latestId, "C");
            Customer c = new Customer();
            c.setCustomerId(nextId);
            CustomerGroup customerGroup = customerGroupService.getCustomerGroupById(customerDTO.getCustomerGroupId());
            c.setCustomerGroup(customerGroup);
            if (!validateDob(customerDTO.getDob())) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Ngày sinh nhỏ hơn ngày hiện tại!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (!customerDTO.getIdentity().replace(" ", "").equals("")) {
                if (!customerRepository.findByIdentity(customerDTO.getIdentity()).isEmpty()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Số chứng minh thư đã tồn tại!");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            if (!customerDTO.getPhoneNumber().replace(" ", "").equals("")) {
                if (!customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber()).isEmpty()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Số điện thoại đã tồn tại!");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            commonMapping(c, customerDTO);

            customerRepository.save(c);
            log.info("----- Add Customer End -----");
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tạo thông tin khách hàng thành công ");
            response.put("customerId", c.getCustomerId());
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.info("Can't add customer", e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Tạo thông tin khách hàng thất bại");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
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

    public ResponseEntity<Map<String, String>> update(String customerId, CustomerDTO customerDTO) throws IOException {
        log.info("----- Update Customer Start -----");

        try {
            Customer c = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("khách hàng có id là " + customerId + " không tồn tại!"));
            CustomerGroup customerGroup = customerGroupService.getCustomerGroupById(customerDTO.getCustomerGroupId());
            c.setCustomerGroup(customerGroup);
            if (!validateDob(customerDTO.getDob())) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Ngày sinh nhỏ hơn ngày hiện tại!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (!customerDTO.getIdentity().replace(" ", "").equals("") && !customerDTO.getIdentity().equals(c.getIdentity())) {
                if (!customerRepository.findByIdentity(customerDTO.getIdentity()).isEmpty()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Số chứng minh thư đã tồn tại!");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            if (!customerDTO.getPhoneNumber().replace(" ", "").equals("")&& !customerDTO.getPhoneNumber().equals(c.getPhoneNumber())) {
                if (!customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber()).isEmpty()) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Số điện thoại đã tồn tại!");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            commonMapping(c, customerDTO);

            customerRepository.save(c);

            log.info("----- Update Customer End -----");

            Map<String, String> response = new HashMap<>();
            response.put("message", "Cập nhật thông tin khách hàng thành công ");
            response.put("customerId", c.getCustomerId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't update customer", e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cập nhật thông tin khách hàng  thất bại");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> delete(String customerId) {
        log.info("----- Delete Customer Start -----");

        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            log.info("Can't delete customer");
            return new ResponseEntity<>("khách hàng có id là " + customerId + " không tồn tại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Customer c = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("khách hàng có id là " + customerId + " không tồn tại!"));
        c.setStatus(Status.NO_ACTIVE);
        customerRepository.save(c);
        log.info("----- Delete Customer End -----");
        return new ResponseEntity<>("Xóa thông tin khách hàng thành công ", HttpStatus.OK);
    }

    public Optional<Customer> getById(String customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("khách hàng có id là " + customerId + " không tồn tại!");
        }
        return customerRepository.findById(customerId);
    }

    public ResponseEntity<Map<String, String>> deleteCustomerByList(List<String> idList) {
        Map<String, String> result = new HashMap<>();

        if (idList == null || idList.isEmpty()) {
            result.put("error", "Danh sách ID không hợp lệ.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        for (String id : idList) {
            ResponseEntity<String> deletionResult = delete(id);
            result.put(id, deletionResult.getBody());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
