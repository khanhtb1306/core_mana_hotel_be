package com.manahotel.be.repository;

import com.manahotel.be.model.entity.InvoiceReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceReservationDetailRepository extends JpaRepository<InvoiceReservationDetail, Long> {
    InvoiceReservationDetail findInvoiceReservationDetailByReservationDetail_ReservationDetailId(Long reservationDetailId);
}
