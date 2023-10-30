package com.manahotel.be.controller;

import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseDTO createOrder(OrderDTO orderDTO) throws IOException {
        return orderService.createOrder(orderDTO);
    }
    @PutMapping
    public ResponseDTO updateTotalPayOrder(Long reservationDetailId) throws IOException {
        return orderService.updateTotalPayOrder(reservationDetailId);
    }
}
