package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findTopByOrderByCustomerIdDesc();
}
