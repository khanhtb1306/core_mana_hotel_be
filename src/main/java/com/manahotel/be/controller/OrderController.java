package com.manahotel.be.controller;

import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.InvoicePrinterService;
import com.manahotel.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private InvoicePrinterService invoicePrinterService;


    @PostMapping
    public ResponseDTO createOrder(OrderDTO orderDTO) throws IOException {
        return orderService.createOrder(orderDTO);
    }

    @PutMapping
    public ResponseDTO updateTotalPayOrder(Long reservationDetailId) throws IOException {
        return orderService.updateTotalPayOrder(reservationDetailId);
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> printBill(String orderId) throws IOException {
        return invoicePrinterService.WriteInvoice(orderId);
    }
}
