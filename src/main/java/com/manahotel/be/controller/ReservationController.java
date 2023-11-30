package com.manahotel.be.controller;

import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.ControlPolicyService;
import com.manahotel.be.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @Autowired
    private ControlPolicyService controlPolicyService;

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
    public ResponseDTO calculateEarlySurcharge(long reservationDetailId, String roomCategoryId, long lateTime, float roomPrice) {
        return controlPolicyService.calculateEarlySurcharge(reservationDetailId, roomCategoryId, lateTime, roomPrice);
    }

    @GetMapping("/calculate-late-surcharge")
    public ResponseDTO calculateLateSurcharge(long reservationDetailId, String roomCategoryId, long lateTime, float roomPrice) {
        return controlPolicyService.calculateLateSurcharge(reservationDetailId, roomCategoryId, lateTime, roomPrice);
    }

    @GetMapping("/calculate_additional_adult_surcharge")
    public ResponseDTO calculateAdditionalAdultSurcharge(long reservationDetailId, String roomCategoryId, long totalAdult, float roomPrice, long timeUse) {
        return controlPolicyService.calculateAdditionalAdultSurcharge(reservationDetailId, roomCategoryId, totalAdult, roomPrice, timeUse);
    }

    @GetMapping("/calculate_additional_children_surcharge")
    public ResponseDTO calculateAdditionalChildrenSurcharge(long reservationDetailId, String roomCategoryId, float roomPrice, List<CustomerDTO> customerDTOS, long timeUse) {
        return controlPolicyService.calculateAdditionalChildrenSurcharge(reservationDetailId, roomCategoryId, roomPrice, customerDTOS, timeUse);
    }
}
