package com.manahotel.be.controller;

import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void createCutomer(CustomerDTO customerDTO) {
         service.create(customerDTO);
    }

    @PutMapping("/{id}")
    public void updateGoods(@PathVariable String id, CustomerDTO customerDTO) {
        service.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGoods(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return service.getById(id);
    }

}