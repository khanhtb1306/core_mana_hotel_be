package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping
    public ResponseDTO getAllReservations() {
        return service.getAllReservationWithRooms();
    }

    @GetMapping("/list-empty-rooms")
    public ResponseDTO getAllEmptyRoomByReservation(Timestamp startDate, Timestamp endDate) {
        return service.getAllEmptyRoomByReservation(startDate, endDate);
    }

    @GetMapping("/{id}")
    public ResponseDTO getReservationById(@PathVariable String id) {
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

    @GetMapping("/list-payment")
    public ResponseDTO getAllPayment() {
        return service.getAllPayment();
    }

    @GetMapping("/calculate-early-surcharge")
    public ResponseDTO calculateEarlySurcharge(String roomCategoryId, long lateTime, float roomPrice) {
        return service.calculateEarlySurcharge(roomCategoryId, lateTime, roomPrice);
    }

    @GetMapping("/calculate-late-surcharge")
    public ResponseDTO calculateLateSurcharge(String roomCategoryId, long lateTime, float roomPrice) {
        return service.calculateLateSurcharge(roomCategoryId, lateTime, roomPrice);
    }
}
