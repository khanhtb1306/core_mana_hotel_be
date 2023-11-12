package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.ChangeRoomRequest;
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
    public ResponseDTO createReservationDetail(ReservationDetailRequest request) {
        return service.createReservationDetail(request.getReservationDetailDTO(), request.getCustomerIds());
    }

    @PutMapping("/{id}")
    public ResponseDTO updateReservationDetail(@PathVariable Long id, ReservationDetailRequest request) {
        return service.updateReservationDetail(id, request.getReservationDetailDTO(), request.getCustomerIds());
    }

    @PutMapping("/change-room")
    public ResponseDTO changeRoomInReservation(ChangeRoomRequest request) {
        return service.changeRoomInReservation(request.getReservationId(), request.getRoomId(), request.getReservationDTO(), request.getReservationDetailDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteReservationDetail(@PathVariable Long id) {
        return service.deleteReservationDetail(id);
    }

    @GetMapping("/calculate-late-surcharge")
    public ResponseDTO calculateLateSurcharge(String roomCategoryId, Long lateTime) {
        return service.calculateLateSurcharge(roomCategoryId, lateTime);
    }
}
