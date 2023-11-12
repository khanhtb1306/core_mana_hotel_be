package com.manahotel.be.controller;

import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.CustomerGroupDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.service.CustomerGroupService;
import com.manahotel.be.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerGroupService groupService;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createCustomer( CustomerDTO customerDTO) throws IOException {
         return service.create(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateCustomer(@PathVariable String id,  CustomerDTO customerDTO) throws IOException {
        return service.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable List<String> id) {
        return service.deleteCustomerByList(id);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/customerGroup")
    public ResponseDTO getAllCustomerGroup() {
        return groupService.getAll();
    }
    @PostMapping("/customerGroup")
    public ResponseDTO createAndUpdateCustomerGroup(CustomerGroupDTO customerGroupDTO) {
        return groupService.createAndUpdateCustomerGroup(customerGroupDTO);
    }
    @DeleteMapping("/customerGroup/{id}")
    public ResponseDTO deleteCustomerGroup(String id)
    {
        return groupService.deleteCustomerGroup(id);
    }


}
