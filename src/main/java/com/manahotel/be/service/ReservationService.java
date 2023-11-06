package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ReservationDetailRepository repository2;

    @Autowired
    private CustomerRepository repository3;

    @Autowired
    private RoomClassRepository repository4;

    @Autowired
    private RoomRepository repository5;

    @Autowired
    private PriceListRepository repository6;

    public ResponseDTO getAllEmptyRoomByReservation(Timestamp startDate, Timestamp endDate) {
        List<Object> list = new ArrayList<>();

        List<RoomCategory> listRoomClasses = repository4.findEmptyRoomCategory(startDate, endDate);

        for (RoomCategory roomClass : listRoomClasses) {
            List<Room> listEmptyRoom = repository5.findEmptyRoom(startDate, endDate, roomClass.getRoomCategoryId());

            Map<String, Object> listInfo = new HashMap<>();

            listInfo.put("roomClass", roomClass);
            listInfo.put("listRoom", listEmptyRoom);
            list.add(listInfo);
        }

        return ResponseUtils.success(list, "Hiển thị phòng trống lịch đặt thành công");
    }

    public ResponseDTO getAllReservationWithRooms() {
        List<Object[]> listReservations = repository.findReservationsWithRooms();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] reservation : listReservations) {
            Reservation r = (Reservation) reservation[0];
            List<ReservationDetail> listReservationDetails = repository2.findReservationDetailByReservation(r);
            Map<String, Object> detailInfo = new HashMap<>();
            detailInfo.put("reservation", r);
            detailInfo.put("listReservationDetails", listReservationDetails.toArray());
            result.add(detailInfo);
        }

        return ResponseUtils.success(result, "Hiển thị lịch đặt phòng thành công");
    }

    public ResponseDTO getAllReservationWithRoomsById(String id) {
        Reservation reservation = findReservation(id);
        Map<String, Object> reservationInfo = new HashMap<>();
        List<ReservationDetail> listReservationDetails = repository2.findReservationDetailByReservation(reservation);
        reservationInfo.put("reservation", reservation);
        reservationInfo.put("listReservationDetails", listReservationDetails.toArray());
        return ResponseUtils.success(reservationInfo, "Hiển thị thành công chi tiết đặt phòng của đơn" + reservation.getReservationId());
    }

    public ResponseDTO createReservation(ReservationDTO reservationDTO) {
        try {
            log.info("----- Begin create reservation -----");
            Long userId = UserUtils.getUser().getStaffId();

            Reservation latestReservation = repository.findTopByOrderByReservationIdDesc();
            String latestId = (latestReservation == null) ? null : latestReservation.getReservationId();
            String nextId = IdGenerator.generateId(latestId, "DP");

            Reservation reservation = new Reservation();
            reservation.setReservationId(nextId);

            commonMapping(reservation, reservationDTO);

            reservation.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            reservation.setCreatedById(userId);

            repository.save(reservation);
            log.info("----- End create reservation -----");

            return ResponseUtils.success(reservation.getReservationId(), "Tạo đơn đặt phòng thành công");
        }
        catch(Exception e) {
            log.info("----- Create reservation failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo đơn đặt phòng thất bại");
        }
    }

    public ResponseDTO updateReservation(String id, ReservationDTO reservationDTO) {
        try {
            log.info("----- Start update reservation -----");
            Reservation reservation = findReservation(id);

            commonMapping(reservation, reservationDTO);

            repository.save(reservation);
            log.info("----- End update reservation -----");

            return ResponseUtils.success(reservation.getReservationId(), "Cập nhật đơn đặt phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Update reservation failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật đơn đặt phòng thất bại");
        }
    }

    private void commonMapping(Reservation reservation, ReservationDTO reservationDTO) {
        Long userId = UserUtils.getUser().getStaffId();

        Customer customer = (reservationDTO.getCustomerId() != null) ? findCustomer(reservationDTO.getCustomerId()) : reservation.getCustomer();
        reservation.setCustomer(customer);

        PriceList priceList = (reservationDTO.getPriceListId() != null) ? findPriceList(reservationDTO.getPriceListId()) : reservation.getPriceList();
        reservation.setPriceList(priceList);

        reservation.setTotalAdults((reservationDTO.getTotalAdults() != null) ? reservationDTO.getTotalAdults() : reservation.getTotalAdults());
        reservation.setTotalChildren((reservationDTO.getTotalChildren() != null) ? reservationDTO.getTotalChildren() : reservation.getTotalChildren());

        reservation.setStatus((reservationDTO.getStatus() != null) ? reservationDTO.getStatus() : reservation.getStatus());
        reservation.setTotalDeposit((reservationDTO.getTotalDeposit() != null) ? reservationDTO.getTotalDeposit() : reservation.getTotalDeposit());
        reservation.setTotalPrice((reservationDTO.getTotalPrice() != null) ? reservationDTO.getTotalPrice() : reservation.getTotalPrice());
        reservation.setDurationStart((reservationDTO.getDurationStart() != null) ? reservationDTO.getDurationStart() : reservation.getDurationStart());
        reservation.setDurationEnd((reservationDTO.getDurationEnd() != null) ? reservationDTO.getDurationEnd() : reservation.getDurationEnd());
        reservation.setNote((reservationDTO.getNote() != null) ? reservationDTO.getNote() : reservation.getNote());

        if(reservation.getStatus().equals(Status.CHECK_IN)) {
             reservation.setStaffCheckIn(userId);
        }
        if (reservation.getStatus().equals(Status.CHECK_OUT)) {
            reservation.setStaffCheckOut(userId);
        }
    }

    private Reservation findReservation(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    private Customer findCustomer(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    private PriceList findPriceList(String id) {
        return repository6.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Price List not found with id " + id));
    }
}
