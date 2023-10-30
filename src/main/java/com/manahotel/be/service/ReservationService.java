package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.EmptyListException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ReservationDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Customer customer = (reservationDTO.getCustomerId() != null) ? findCustomer(reservationDTO.getCustomerId()) : reservation.getCustomer();
        reservation.setCustomer(customer);

        reservation.setTotalAdults((reservationDTO.getTotalAdults() != null) ? reservationDTO.getTotalAdults() : reservation.getTotalAdults());
        reservation.setTotalChildren((reservationDTO.getTotalChildren() != null) ? reservationDTO.getTotalChildren() : reservation.getTotalChildren());
        reservation.setCheckInEstimate((reservationDTO.getCheckInEstimate() != null) ? reservationDTO.getCheckInEstimate() : reservation.getCheckInEstimate());
        reservation.setCheckOutEstimate((reservationDTO.getCheckOutEstimate() != null) ? reservationDTO.getCheckOutEstimate() : reservation.getCheckOutEstimate());
        reservation.setCheckInActual((reservationDTO.getCheckInActual() != null) ? reservationDTO.getCheckInActual() : reservation.getCheckInActual());
        reservation.setCheckOutActual((reservationDTO.getCheckOutActual() != null) ? reservationDTO.getCheckOutActual() : reservation.getCheckOutActual());
        reservation.setStatus((reservationDTO.getStatus() != null) ? reservationDTO.getStatus() : reservation.getStatus());
        reservation.setTotalDeposit((reservationDTO.getTotalDeposit() != null) ? reservationDTO.getTotalDeposit() : reservation.getTotalDeposit());
        reservation.setTotalPrice((reservationDTO.getTotalPrice() != null) ? reservationDTO.getTotalPrice() : reservation.getTotalPrice());
    }

    private Reservation findReservation(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    private Customer findCustomer(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }
}
