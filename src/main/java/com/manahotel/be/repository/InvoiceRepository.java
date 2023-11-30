package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Invoice findTopByOrderByInvoiceIdDesc();
}
