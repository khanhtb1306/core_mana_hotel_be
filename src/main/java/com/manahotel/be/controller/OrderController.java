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
    @PutMapping("/{id}")
    public ResponseDTO updateOrder(@PathVariable String id,OrderRequest orderRequest){
        return orderService.updateOrder(id,orderRequest.getOrderDetailDTOList());
    }
    @PutMapping("/updateStatus/{id}")
    public ResponseDTO updateStatusOrder(@PathVariable String id,String status){
        return orderService.updateStatucOrder(id,status);
    }
    @DeleteMapping("/{id}")
    public ResponseDTO deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/print/{id}")
    public ResponseEntity<ByteArrayResource> printBill(@PathVariable String id) {
        return invoicePrinterService.WriteInvoice(id.replace("\n", ""));
    }

    @GetMapping("/{id}")
    public ResponseDTO getOrderDetails(@PathVariable String id){
        return orderDetailService.getOrderDetails(id.replace("\n", ""));
    }
}
