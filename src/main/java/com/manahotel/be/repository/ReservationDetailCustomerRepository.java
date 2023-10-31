package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDetailCustomerRepository extends JpaRepository<ReservationDetailCustomer, Long> {

    List<ReservationDetailCustomer> findReservationDetailCustomerByReservationDetail(ReservationDetail reservationDetail);
}
