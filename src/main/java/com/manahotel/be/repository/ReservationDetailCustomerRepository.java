package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDetailCustomerRepository extends JpaRepository<ReservationDetailCustomer, Long> {

    List<ReservationDetailCustomer> findReservationDetailCustomerByReservationDetail(ReservationDetail reservationDetail);

    @Query(value = "DELETE FROM reservation_detail_customer rdc WHERE rdc.reservation_detail_id = :id", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteReservationDetailCustomerByReservationDetailId(Long id);
}
