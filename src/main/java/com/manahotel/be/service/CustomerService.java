package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.entity.Customer;
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
import java.util.logging.SimpleFormatter;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    private void commonMapping(Customer customer, CustomerDTO customerDTO) throws IOException {
        customer.setCustomerName((customerDTO.getCustomerName() != null && !customerDTO.getCustomerName().isEmpty()) ? customerDTO.getCustomerName() : customer.getCustomerName());
        customer.setCustomerGroup(customerDTO.getCustomerGroup() != null ? customerDTO.getCustomerGroup() : customer.getCustomerGroup());
        customer.setPhoneNumber(customerDTO.getPhoneNumber() != null ? customerDTO.getPhoneNumber() : customer.getPhoneNumber());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(customerDTO.getDob());
            Timestamp dobTimestamp = new Timestamp(parsedDate.getTime());
            customer.setDob(dobTimestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        customer.setEmail(customerDTO.getEmail() != null ? customerDTO.getEmail() : customer.getEmail());
        customer.setAddress(customerDTO.getAddress() != null ? customerDTO.getAddress() : customer.getAddress());
        customer.setIdentity(customerDTO.getIdentity() != null ? customerDTO.getIdentity() : customer.getIdentity());
        customer.setNationality(customerDTO.getNationality() != null ? customerDTO.getNationality() : customer.getNationality());
        customer.setTaxCode(customerDTO.getTaxCode() != null ? customerDTO.getTaxCode() : customer.getTaxCode());
        customer.setGender(customerDTO.isGender());
        customer.setImage(customerDTO.getImage() != null ? customerDTO.getImage().getBytes() : null);

    }

    public ResponseEntity<String> create(CustomerDTO customerDTO) throws IOException {
        log.info("----- Add Customer Start -----");
        try {
            Customer latestCustomer = customerRepository.findTopByOrderByCustomerIdDesc();
            String latestId = (latestCustomer == null) ? null : latestCustomer.getCustomerId();
            String nextId = IdGenerator.generateId(latestId, "C");
            if (customerDTO.getCustomerName() == null) {
                log.info("Name customer is null");
                return new ResponseEntity<>("Tên khách hàng bị trống", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Customer c = new Customer();
            c.setCustomerId(nextId);

            commonMapping(c, customerDTO);

            customerRepository.save(c);
            log.info("----- Add Customer End -----");
            return new ResponseEntity<>("Tạo thông tin khách hàng thành công ", HttpStatus.OK);

        } catch (Exception e) {
            log.info("Can't add customer", e.getMessage());
            return new ResponseEntity<>("Tạo thông tin khách hàng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<String> update(String customerId, CustomerDTO customerDTO) throws IOException {
        log.info("----- Update Customer Start -----");

        try {
            if (customerDTO.getCustomerName() == null) {
                log.info("Name customer is null");
                return new ResponseEntity<>("Tên khách hàng bị trống", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Customer c = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("customer with id " + customerId + " not exists"));

            commonMapping(c, customerDTO);

            customerRepository.save(c);

            log.info("----- Update Customer End -----");
            return new ResponseEntity<>("Cập nhật thông tin khách hàng thành công ", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't update customer", e.getMessage());
            return new ResponseEntity<>("Cập nhật thông tin khách hàng  thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> delete(String customerId) {
        log.info("----- Delete Customer Start -----");

        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            log.info("Can't delete customer");
            return new ResponseEntity<>("customer with id " + customerId + " not exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        customerRepository.deleteById(customerId);

        log.info("----- Delete Customer End -----");
        return new ResponseEntity<>("Xóa thông tin khách hàng thành công ", HttpStatus.OK);
    }

    public Optional<Customer> getById(String customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + customerId + " not exists");
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
