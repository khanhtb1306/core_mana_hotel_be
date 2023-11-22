package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ReservationDetailCustomerDTO;
import com.manahotel.be.model.dto.ReservationDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.request.ChangeRoomRequest;
import com.manahotel.be.service.ReservationDetailCustomerService;
import com.manahotel.be.service.ReservationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation-detail")
public class ReservationDetailController {

    @Autowired
    private ReservationDetailService service;

    @Autowired
    private ReservationDetailCustomerService service2;

    @GetMapping("/list-customers/{id}")
    public ResponseDTO getListCustomersByReservationDetailId(@PathVariable Long id) {
        return service.getListCustomersByReservationDetailId(id);
    }

    @PostMapping
    public ResponseDTO createReservationDetail(ReservationDetailDTO reservationDetailDTO) {
        return service.createReservationDetail(reservationDetailDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO updateReservationDetail(@PathVariable Long id, ReservationDetailDTO reservationDetailDTO) {
        return service.updateReservationDetail(id, reservationDetailDTO);
    }

    @PutMapping("/change-room")
    public ResponseDTO changeRoomInReservation(ChangeRoomRequest request) {
        return service.changeRoomInReservation(request.getReservationId(), request.getRoomId(), request.getReservationDTO(), request.getReservationDetailDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteReservationDetail(@PathVariable Long id) {
        return service.deleteReservationDetail(id);
    }

    @PostMapping("/reservation-detail-customer")
    public ResponseDTO createRDCustomer(ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        return service2.createRDCustomer(reservationDetailCustomerDTO);
    }

    @PutMapping("/reservation-detail-customer/{id}")
    public ResponseDTO updateRDCustomer(@PathVariable Long id, ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        return service2.updateRDCustomer(id, reservationDetailCustomerDTO);
    }

    @DeleteMapping("/reservation-detail-customer/{id}")
    public ResponseDTO deleteRDCustomer(@PathVariable Long id) {
        return service2.deleteRDCustomer(id);
    }
}
