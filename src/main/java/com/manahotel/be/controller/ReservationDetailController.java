package com.manahotel.be.controller;

import com.manahotel.be.model.dto.request.ListTimePrice;
import com.manahotel.be.model.dto.request.PriceHistoryOverTimeRequest;
import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.request.ChangeRoomRequest;
import com.manahotel.be.model.dto.request.ReservationDetailCustomerRequest;
import com.manahotel.be.service.ReservationDetailCustomerService;
import com.manahotel.be.service.ReservationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

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
        return service.changeRoomInReservation(request.getReservationId(), request.getRoomId(), request.getReservationDetailDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteReservationDetail(@PathVariable Long id) {
        return service.deleteReservationDetail(id);
    }

    @PostMapping("/reservation-detail-customer")
    public ResponseDTO createRDCustomer(ReservationDetailCustomerRequest request) {
        return service2.createRDCustomer(request.getReservationDetailCustomerDTO(), request.isAdult());
    }

    @PutMapping("/reservation-detail-customer")
    public ResponseDTO updateRDCustomer(ReservationDetailCustomerRequest request) {
        return service2.updateRDCustomer(request.getReservationDetailCustomerDTO(), request.isCheck());
    }

    @DeleteMapping("/reservation-detail-customer/{id}")
    public ResponseDTO deleteRDCustomer(@PathVariable Long id, boolean isAdult) {
        return service2.deleteRDCustomer(id, isAdult);
    }

    @GetMapping("/get-by-date")
    public ResponseDTO getReservationDetailByDate(Timestamp start, Timestamp end) {
        return service.getReservationDetailByDate(start, end);
    }

    @GetMapping("/check-duplicate-booking")
    public ResponseDTO checkDuplicateBooking(Timestamp start, Timestamp end, String roomId, Long reservationDetailId) {
        return service.checkDuplicateBooking(start, end, roomId, reservationDetailId);
    }

    @GetMapping("/check_customer_is_visitor")
    public ResponseDTO checkCustomerIsVisitor(String reservationId) {
        return service2.checkCustomerIsVisitor(reservationId);
    }
    @GetMapping("/get_price_history_over_time")
    public ResponseDTO GetPriceHistoryOverTime(String reservationId) {
        return service.GetPriceHistoryOverTime(reservationId);
    }

    @PostMapping("/update_price_History_ver_time")
    public ResponseDTO UpdatePriceHistoryOverTime(PriceHistoryOverTimeRequest request) {
        return service.UpdatePriceHistoryOverTime(request.getTimePrices(), request.getReservationDetailId());
    }



}
