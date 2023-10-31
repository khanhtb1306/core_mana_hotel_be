package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ReservationDetailCustomerDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ReservationDetailCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation-detail-customer")
public class ReservationDetailCustomerController {

    @Autowired
    private ReservationDetailCustomerService service;

    @PostMapping
    public ResponseDTO createRDCustomer(ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        return service.createRDCustomer(reservationDetailCustomerDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateRDCustomer(@PathVariable Long id, ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        return service.updateRDCustomer(id, reservationDetailCustomerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteRDCustomer(@PathVariable Long id) {
        return service.deleteRDCustomer(id);
    }
}
