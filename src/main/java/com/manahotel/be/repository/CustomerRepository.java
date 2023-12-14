package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findTopByOrderByCustomerIdDesc();
    List<Customer> findByIdentity(String identity);
    List<Customer> findByPhoneNumber(String identity);

}
