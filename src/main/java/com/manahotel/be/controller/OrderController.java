package com.manahotel.be.controller;

import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.OrderRequest;
import com.manahotel.be.service.InvoicePrinterService;
import com.manahotel.be.service.OrderDetailService;
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
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseDTO createOrder(OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest.getOrderDTO(),orderRequest.getOrderDetailDTOList());
    }
    @PutMapping
    public ResponseDTO updateOrder(String orderId,OrderRequest orderRequest) {
        return orderService.updateOrder(orderId,orderRequest.getOrderDetailDTOList());
    }

    @DeleteMapping
    public ResponseDTO deleteOrder(String orderId) {
        return orderService.deleteOrder(orderId);
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> printBill(String orderId) {
        return invoicePrinterService.WriteInvoice(orderId.replace("\n", ""));
    }

    @GetMapping("/{id}")
    public ResponseDTO getOrderDetails(String id){
        return orderDetailService.getOrderDetails(id.replace("\n", ""));
    }
}
