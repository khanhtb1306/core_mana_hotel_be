package com.manahotel.be.controller;

import com.manahotel.be.model.dto.request.ChildrenSurchargeRequest;
import com.manahotel.be.model.dto.response.CustomerDTO;
import com.manahotel.be.model.dto.response.ReservationDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.service.ControlPolicyService;
import com.manahotel.be.service.FundBookService;
import com.manahotel.be.service.ReservationService;
import com.manahotel.be.service.RoomClassService;
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

    @Autowired
    private FundBookService fundBookService;

    @Autowired
    private RoomClassService roomClassService;

    @GetMapping
    public ResponseDTO getAllReservations(Timestamp start, Timestamp end) {
        return service.getAllReservationWithRooms(start, end);
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

    @PostMapping("/calculate_additional_children_surcharge")
    public ResponseDTO calculateAdditionalChildrenSurcharge(ChildrenSurchargeRequest request) {
        return controlPolicyService.calculateAdditionalChildrenSurcharge(request.getReservationDetailId(), request.getRoomCategoryId(), request.getRoomPrice(), request.getCustomerDTOS(), request.getTimeUse(), request.isStatus());
    }

    @GetMapping("/calculate_deposit_cancel_reservation")
    public ResponseDTO calculateDepositCancelReservation(float deposit, float number, String reservationId, boolean checkFundBook) {
        return controlPolicyService.calculateDepositCancelReservation(deposit, number, reservationId, checkFundBook);
    }

    @GetMapping("/get_control_policy_by_reservation_detail")
    public ResponseDTO getControlPolicy(long reservationDetailId, String policyName) {
        return controlPolicyService.getControlPolicyByReservationDetail(reservationDetailId, policyName);
    }
    @GetMapping("/get_control_policy_by_reservation")
    public ResponseDTO getControlPolicyByReservation(String reservationId) {
        return controlPolicyService.getControlPolicyByReservation(reservationId);
    }
    @GetMapping("/get_fund_book_by_reservation")
    public ResponseDTO getFundBookByReservation(String reservationId) {
        return fundBookService.getFundBookByReservation(reservationId);
    }

    @GetMapping("/get_active_room_class_with_active_rooms")
    public ResponseDTO findActiveRoomCategoriesWithActiveRooms() {
        return roomClassService.findActiveRoomCategoriesWithActiveRooms();
    }
}
