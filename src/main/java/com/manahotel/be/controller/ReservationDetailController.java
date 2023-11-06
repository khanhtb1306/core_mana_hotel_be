package com.manahotel.be.controller;

import com.manahotel.be.model.dto.request.ReservationDetailRequest;
import com.manahotel.be.service.ReservationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation-detail")
public class ReservationDetailController {

    @Autowired
    private ReservationDetailService service;

    @PostMapping
    public String createReservationDetail(ReservationDetailRequest request) {
        return service.createReservationDetail(request.getReservationDetailDTO(), request.getCustomerIds());
    }

    @PutMapping("/{id}")
    public String updateReservationDetail(@PathVariable Long id, ReservationDetailRequest request) {
        return service.updateReservationDetail(id, request.getReservationDetailDTO(), request.getCustomerIds());
    }
}
