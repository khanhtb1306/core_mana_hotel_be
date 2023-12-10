package com.manahotel.be.controller;

import com.manahotel.be.model.dto.InvoiceDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.InvoicePurchaseRequest;
import com.manahotel.be.model.dto.request.InvoiceRequest;
import com.manahotel.be.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseDTO getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
    @GetMapping("/reservation/{id}")
    public ResponseDTO getInvoiceByReservation(@PathVariable String id) {
        return invoiceService.getInvoiceByReservation(id);
    }


    @GetMapping("/{id}")
    public ResponseDTO getInvoiceById(@PathVariable String id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("/reservation")
    public ResponseDTO createReservationInvoice(InvoiceRequest request) {
        return invoiceService.createReservationInvoice(request.getReservationDetailDTO(), request.getInvoiceDTO());
    }

    @PostMapping("/purchase")
    public ResponseDTO createPurchaseInvoice(InvoicePurchaseRequest request) {
        return invoiceService.createPurchaseInvoice(request.getInvoiceDTO(), request.getOrderDetailDTOList());
    }
}
