package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ReservationDetailRoomDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ReservationDetailRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation-detail-room")
public class ReservationDetailRoomController {

    @Autowired
    private ReservationDetailRoomService service;

    @PostMapping
    public ResponseDTO createRDRoom(ReservationDetailRoomDTO reservationDetailRoomDTO) {
        return service.createRDRoom(reservationDetailRoomDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateRDRoom(@PathVariable Long id, ReservationDetailRoomDTO reservationDetailRoomDTO) {
        return service.updateRDRoom(id, reservationDetailRoomDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteRDRoom(@PathVariable Long id) {
        return service.deleteRDRoom(id);
    }
}
