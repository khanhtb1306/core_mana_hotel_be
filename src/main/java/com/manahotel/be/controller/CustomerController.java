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
    public String createCustomer(@RequestBody CustomerDTO customerDTO) {
         return service.create(customerDTO);
    }

    @PutMapping("/{id}")
    public String updateGoods(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        return service.update(id, customerDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteGoods(@PathVariable String id) {
        return service.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return service.getById(id);
    }

}
