package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.InvoiceDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.Invoice;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.InvoiceRepository;
import com.manahotel.be.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private CustomerRepository repository2;

    @Autowired
    private ReservationRepository repository3;

    public ResponseDTO getAllInvoices() {
        return ResponseUtils.success(repository.findAll(), "Hiển thị danh sách hóa đơn thành công");
    }

    public ResponseDTO getInvoiceById(String id) {
        return ResponseUtils.success(findInvoice(id), "Hiển thị chi tiết hóa đơn thành công");
    }

    public ResponseDTO createInvoice(InvoiceDTO invoiceDTO) {
        try {
            log.info("----- Start create invoice -----");
            Invoice latestInvoice = repository.findTopByOrderByInvoiceIdDesc();
            String latestId = (latestInvoice == null) ? null : latestInvoice.getInvoiceId();
            String nextId = IdGenerator.generateId(latestId, "HD");

            Invoice invoice = new Invoice();
            invoice.setInvoiceId(nextId);
            invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            invoice.setCreatedById(UserUtils.getUser().getStaffId());
            invoice.setCustomer(findCustomer(invoiceDTO.getCustomerId()));
            invoice.setReservation(findReservation(invoiceDTO.getReservationId()));
            invoice.setTotal(invoiceDTO.getTotal());
            invoice.setDiscount(invoiceDTO.getDiscount());
            invoice.setStatus(Status.COMPLETE);
            invoice.setNote(invoiceDTO.getNote());

            repository.save(invoice);
            log.info("----- End create invoice -----");
            return ResponseUtils.success(invoice.getInvoiceId(), "Tạo hóa đơn thành công");
        }
        catch (Exception e) {
            log.info("----- Create invoice failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo hóa đơn thất bại");
        }
    }

    private Invoice findInvoice(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + id));
    }

    private Customer findCustomer(String id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    private Reservation findReservation(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }
}
