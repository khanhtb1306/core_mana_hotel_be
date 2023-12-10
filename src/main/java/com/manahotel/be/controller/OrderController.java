package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.OrderRequest;
import com.manahotel.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/reservation/{id}")
    public ResponseDTO getOrderByReservationId(@PathVariable Long id){
        return orderService.getOrderByReservationDetailId(id);
    }
    @GetMapping("/by_reservation/{id}")
    public ResponseDTO getOrderByReservationId(@PathVariable String id){
        return orderService.getOrderByReservationId(id);
    }
    @PostMapping
    public ResponseDTO createOrder(OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest.getOrderDTO(),orderRequest.getOrderDetailDTOList());
    }
    @PutMapping("/{id}")
    public ResponseDTO updateOrder(@PathVariable String id,OrderRequest orderRequest){
        return orderService.updateOrder(id,orderRequest.getOrderDetailDTOList());
    }
    @PutMapping("/updateStatus/{id}")
    public ResponseDTO updateStatusOrder(@PathVariable String id, String status, String paidMethod){
        return orderService.updateStatusOrder(id, status, paidMethod);
    }
    @DeleteMapping("/{id}")
    public ResponseDTO deleteOrder(@PathVariable String id) {
        return orderService.deleteOrder(id);
    }
    
    @GetMapping("/get-all-retail-payment")
    public ResponseDTO getAllRetailPayment() {
        return orderService.getAllRetailPayment();
    }
}
