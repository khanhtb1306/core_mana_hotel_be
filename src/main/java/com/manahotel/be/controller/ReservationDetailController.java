package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ReservationDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ReservationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation-detail")
public class ReservationDetailController {

    @Autowired
    private ReservationDetailService service;

    @PostMapping
    public ResponseDTO createReservationDetail(ReservationDetailDTO reservationDetailDTO) {
        return service.createReservationDetail(reservationDetailDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateReservationDetail(@PathVariable Long id, ReservationDetailDTO reservationDetailDTO) {
        return service.updateReservationDetail(id, reservationDetailDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteReservationDetail(@PathVariable Long id) {
        return service.deleteReservationDetail(id);
    }
}
