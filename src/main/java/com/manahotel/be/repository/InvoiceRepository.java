package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Invoice findTopByOrderByInvoiceIdDesc();

    List<Invoice> findAllByOrderByInvoiceIdDesc();
}
