package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findTopByOrderByCustomerIdDesc();
    Customer findByIdentity(String identity);
}
