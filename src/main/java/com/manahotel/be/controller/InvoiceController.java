package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @GetMapping
    public ResponseDTO getAllInvoices() {
        return service.getAllInvoices();
    }

    @GetMapping("/{id}")
    public ResponseDTO getInvoiceById(@PathVariable String id) {
        return service.getInvoiceById(id);
    }
}
