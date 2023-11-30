package com.manahotel.be.service;

import com.manahotel.be.common.constant.PolicyCont;
import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ControlPolicyUtils;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.BookingConflictException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ReservationDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReservationDetailService {

    @Autowired
    private ReservationDetailRepository repository;

    @Autowired
    private ReservationRepository repository2;

    @Autowired
    private RoomRepository repository3;

    @Autowired
    private ReservationDetailCustomerService service;

    public ResponseDTO getListCustomersByReservationDetailId(Long id) {
        return service.getListCustomersByReservationDetailId(id);
    }

    public ResponseDTO createReservationDetail(ReservationDetailDTO reservationDetailDTO) {
        try {
            log.info("----- Start create detail for reservation ------");
            ReservationDetail reservationDetail = new ReservationDetail();

            reservationDetail.setReservationDetailStatus(Status.ACTIVATE);

            commonMapping(reservationDetail, reservationDetailDTO);

            log.info("----- End create detail for reservation ------");

            return ResponseUtils.success(reservationDetail.getReservationDetailId(), "Tạo chi tiết đặt phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Create detail for reservation failed ------\n" + e.getMessage());
            return ResponseUtils.error("Tạo chi tiết đặt phòng thất bại");
        }
    }

    public ResponseDTO updateReservationDetail(Long id, ReservationDetailDTO reservationDetailDTO) {
        try {
            log.info("----- Start update detail for reservation ------");
            ReservationDetail reservationDetail = findReservationDetail(id);

            commonMapping(reservationDetail, reservationDetailDTO);

            log.info("----- End update detail for reservation ------");

            return ResponseUtils.success(reservationDetail.getReservationDetailId(), "Cập nhật chi tiết đặt phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Update detail for reservation failed ------\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật chi tiết đặt phòng thất bại");
        }
    }

    public ResponseDTO changeRoomInReservation(String reservationId, String roomId, ReservationDTO reservationDTO, ReservationDetailDTO reservationDetailDTO) {
        try {
            log.info("----- Start changing room -----");
            ReservationDetail reservationDetail = repository.findReservationDetailByReservationAndRoom(findReservation(reservationId), findRoom(roomId));

            Room oldRoom = reservationDetail.getRoom();
            oldRoom.setBookingStatus(Status.ROOM_EMPTY);
            repository3.save(oldRoom);

            Room newRoom = (reservationDetailDTO.getRoomId() != null) ? findRoom(reservationDetailDTO.getRoomId()) : reservationDetail.getRoom();
            reservationDetail.setRoom(newRoom);

            if(!oldRoom.getRoomCategory().getRoomCategoryId().equals(newRoom.getRoomCategory().getRoomCategoryId())) {
                reservationDetail.setPrice((reservationDetailDTO.getPrice() != null) ? reservationDetailDTO.getPrice() : reservationDetail.getPrice());
            }

            if(reservationDetail.getStatus().equals(Status.CHECK_IN)) {
                newRoom.setBookingStatus(Status.ROOM_USING);
            }

            repository3.save(newRoom);

            repository.save(reservationDetail);
            log.info("----- End changing room -----");

            return ResponseUtils.success("Thay đổi phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Change room failed ------\n" + e.getMessage());
            return ResponseUtils.error("Thay đổi phòng thất bại");
        }
    }

    public ResponseDTO deleteReservationDetail(Long id) {
        try {
            log.info("----- Start delete Reservation Detail -----");
            ReservationDetail reservationDetail = findReservationDetail(id);
            reservationDetail.setReservationDetailStatus(Status.DELETE);
            repository.save(reservationDetail);
            log.info("----- End delete Reservation Detail -----");
            return ResponseUtils.success("Xóa chi tiết đặt phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Delete Reservation Detail failed ------\n" + e.getMessage());
            return ResponseUtils.error("Xóa chi tiết đặt phòng thất bại");
        }
    }

    private void commonMapping(ReservationDetail reservationDetail, ReservationDetailDTO reservationDetailDTO) {

        Reservation reservation = (reservationDetailDTO.getReservationId() != null) ? findReservation(reservationDetailDTO.getReservationId()) : reservationDetail.getReservation();
        reservationDetail.setReservation(reservation);

        Room room = (reservationDetailDTO.getRoomId() != null) ? findRoom(reservationDetailDTO.getRoomId()) : reservationDetail.getRoom();
        reservationDetail.setRoom(room);

        reservationDetail.setStatus((reservationDetailDTO.getStatus() != null) ? reservationDetailDTO.getStatus() : reservationDetail.getStatus());

        switch (reservationDetail.getStatus()) {
            case Status.BOOKING -> {
                reservationDetail.setCheckInEstimate((reservationDetailDTO.getCheckInEstimate() != null) ? reservationDetailDTO.getCheckInEstimate() : reservationDetail.getCheckInEstimate());
                reservationDetail.setCheckOutEstimate((reservationDetailDTO.getCheckOutEstimate() != null) ? reservationDetailDTO.getCheckOutEstimate() : reservationDetail.getCheckOutEstimate());

                checkDuplicateBooking(reservationDetail.getCheckInEstimate(), reservationDetail.getCheckOutEstimate(), reservationDetail.getRoom(), reservationDetail.getReservationDetailId());
            }
            case Status.CHECK_IN -> {
                reservationDetail.setCheckInActual((reservationDetailDTO.getCheckInActual() != null) ? reservationDetailDTO.getCheckInActual() : reservationDetail.getCheckInActual());
                reservationDetail.setCheckOutEstimate((reservationDetailDTO.getCheckOutEstimate() != null) ? reservationDetailDTO.getCheckOutEstimate() : reservationDetail.getCheckOutEstimate());

                checkDuplicateBooking(reservationDetail.getCheckInActual(), reservationDetail.getCheckOutEstimate(), reservationDetail.getRoom(), reservationDetail.getReservationDetailId());

                room.setBookingStatus(Status.ROOM_USING);
            }
            case Status.CHECK_OUT -> {
                reservationDetail.setCheckOutActual((reservationDetailDTO.getCheckOutActual() != null) ? reservationDetailDTO.getCheckOutActual() : reservationDetail.getCheckOutActual());

                checkDuplicateBooking(reservationDetail.getCheckInActual(), reservationDetail.getCheckOutActual(), reservationDetail.getRoom(), reservationDetail.getReservationDetailId());

                room.setBookingStatus(Status.ROOM_EMPTY);
            }
        }

        repository3.save(room);

        reservationDetail.setPrice((reservationDetailDTO.getPrice() != null) ? reservationDetailDTO.getPrice() : reservationDetail.getPrice());
        reservationDetail.setReservationType((reservationDetailDTO.getReservationType() != null) ? reservationDetailDTO.getReservationType() : reservationDetail.getReservationType());

        repository.save(reservationDetail);

        Timestamp start = repository2.findMinCheckIn(reservation);
        Timestamp end = repository2.findMaxCheckOut(reservation);

        reservation.setDurationStart(start);
        reservation.setDurationEnd(end);

        repository2.save(reservation);
    }

    private ReservationDetail findReservationDetail(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail not found with id " + id));
    }

    private Reservation findReservation(String id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    private Room findRoom(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + id));
    }

    private void checkDuplicateBooking(Timestamp checkIn, Timestamp checkOut, Room room, Long reservationDetailId) {
        List<ReservationDetail> listReservationDetails = repository.checkBooking(checkIn, checkOut, room, reservationDetailId);

        if(!listReservationDetails.isEmpty()) {
            throw new BookingConflictException("Lịch phòng " + room.getRoomName() + " đang trùng với các lịch khác");
        }
    }
}
