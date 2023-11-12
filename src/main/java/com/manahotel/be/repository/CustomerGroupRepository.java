package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerGroupRepository extends JpaRepository<CustomerGroup,String> {
    CustomerGroup findTopByOrderByCustomerGroupIdDesc();
}