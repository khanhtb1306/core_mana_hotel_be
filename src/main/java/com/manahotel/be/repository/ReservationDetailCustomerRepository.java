package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReservationDetailCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDetailCustomerRepository extends JpaRepository<ReservationDetailCustomer, Long> {
}
