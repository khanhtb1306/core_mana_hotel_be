package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    private void commonMapping(Customer c, CustomerDTO customerDTO) {
        c.setCustomerName(customerDTO.getCustomerName());
        c.setCustomerGroup(customerDTO.getCustomerGroup());
        c.setPhoneNumber(customerDTO.getPhoneNumber());
        c.setDob(customerDTO.getDob());
        c.setEmail(customerDTO.getEmail());
        c.setAddress(customerDTO.getAddress());
        c.setIdentity(customerDTO.getIdentity());
        c.setNationality(customerDTO.getNationality());
        c.setTaxCode(customerDTO.getTaxCode());
        c.setGender(customerDTO.isGender());
    }

    public String create(CustomerDTO customerDTO) {
        Customer latestCustomer = customerRepository.findTopByOrderByCustomerIdDesc();
        String latestId = (latestCustomer == null) ? null : latestCustomer.getCustomerId();
        String nextId = IdGenerator.generateId(latestId, "C");

        Customer c = new Customer();
        c.setCustomerId(nextId);

        commonMapping(c, customerDTO);

        customerRepository.save(c);

        return "Tạo thông tin khách hàng thành công";
    }

    public String update(String customerId, CustomerDTO customerDTO) {

        Customer c = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("customer with id " + customerId + " not exists"));

        commonMapping(c, customerDTO);

        customerRepository.save(c);

        return "Cập nhật thông tin khách hàng thành công";
    }

    public String delete(String customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + customerId + " not exists");
        }
        customerRepository.deleteById(customerId);

        return "Xóa thông tin khách hàng thành công";
    }

    public Optional<Customer> getById(String customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + customerId + " not exists");
        }
        return customerRepository.findById(customerId);
    }

}
