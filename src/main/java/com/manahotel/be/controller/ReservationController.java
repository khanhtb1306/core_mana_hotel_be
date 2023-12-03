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
    public ResponseDTO getAllEmptyRoomByReservation(Timestamp startDate, Timestamp endDate, String reservationId) {
        return service.getAllEmptyRoomByReservation(startDate, endDate, reservationId);
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
    public ResponseDTO calculateEarlySurcharge(long reservationDetailId, String roomCategoryId, long lateTime, float roomPrice, boolean status) {
        return controlPolicyService.calculateEarlySurcharge(reservationDetailId, roomCategoryId, lateTime, roomPrice, status);
    }

    @GetMapping("/calculate-late-surcharge")
    public ResponseDTO calculateLateSurcharge(long reservationDetailId, String roomCategoryId, long lateTime, float roomPrice, boolean status) {
        return controlPolicyService.calculateLateSurcharge(reservationDetailId, roomCategoryId, lateTime, roomPrice, status);
    }

    @GetMapping("/calculate_additional_adult_surcharge")
    public ResponseDTO calculateAdditionalAdultSurcharge(long reservationDetailId, String roomCategoryId, long totalAdult, float roomPrice, long timeUse, boolean status) {
        return controlPolicyService.calculateAdditionalAdultSurcharge(reservationDetailId, roomCategoryId, totalAdult, roomPrice, timeUse, status);
    }

    @GetMapping("/calculate_additional_children_surcharge")
    public ResponseDTO calculateAdditionalChildrenSurcharge(long reservationDetailId, String roomCategoryId, float roomPrice, List<CustomerDTO> customerDTOS, long timeUse, boolean status) {
        return controlPolicyService.calculateAdditionalChildrenSurcharge(reservationDetailId, roomCategoryId, roomPrice, customerDTOS, timeUse, status);
    }

    @GetMapping("/get_control_policy_by_reservation_detail")
    public ResponseDTO getControlPolicy(long reservationDetailId, String policyName) {
        return controlPolicyService.getControlPolicyByReservationDetail(reservationDetailId, policyName);
    }
}
