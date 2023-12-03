package com.manahotel.be.repository;

import com.manahotel.be.model.entity.InvoiceReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceReservationDetailRepository extends JpaRepository<InvoiceReservationDetail, Long> {
}
