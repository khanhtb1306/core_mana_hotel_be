package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping
    public ResponseDTO getAllReservations() {
        return service.getAllReservationWithRooms();
    }

    @GetMapping("/{id}")
    public ResponseDTO getReservationById(String id) {
        return service.getAllReservationWithRoomsById(id);
    }

    @PostMapping
    public ResponseDTO createReservation(ReservationDTO reservationDTO) {
        return service.createReservation(reservationDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateReservation(@PathVariable String id, ReservationDTO reservationDTO) {
        return service.updateReservation(id, reservationDTO);
    }
}
