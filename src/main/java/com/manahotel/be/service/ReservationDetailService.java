package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String createReservationDetail(ReservationDetailDTO reservationDetailDTO, List<String> customerIds) {
        try {
            log.info("----- Start create detail for reservation ------");
            ReservationDetail reservationDetail = new ReservationDetail();

            commonMapping(reservationDetail, reservationDetailDTO);

            repository.save(reservationDetail);
            log.info("----- End create detail for reservation ------");

            log.info("----- Start create RDC -----");
            service.createRDCustomer(customerIds, reservationDetail.getReservationDetailId());
            log.info("----- End create RDC -----");

            return "Tạo chi tiết đặt phòng thành công";
        }
        catch (Exception e) {
            log.info("----- Create detail for reservation failed ------\n" + e.getMessage());
            return "Tạo chi tiết đặt phòng thất bại";
        }
    }

    public String updateReservationDetail(Long id, ReservationDetailDTO reservationDetailDTO, List<String> customerIds) {
        try {
            log.info("----- Start update detail for reservation ------");
            ReservationDetail reservationDetail = findReservationDetail(id);

            commonMapping(reservationDetail, reservationDetailDTO);

            repository.save(reservationDetail);
            log.info("----- End update detail for reservation ------");

            log.info("----- Start create RDC -----");
            service.createRDCustomer(customerIds, reservationDetail.getReservationDetailId());
            log.info("----- End create RDC -----");

            return "Cập nhật chi tiết đặt phòng thành công";
        }
        catch (Exception e) {
            log.info("----- Update detail for reservation failed ------\n" + e.getMessage());
            return "Cập nhật chi tiết đặt phòng thất bại";
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
            }
            case Status.CHECK_IN -> {
                if (reservationDetail.getCheckInEstimate() == null && reservationDetail.getCheckOutEstimate() == null) {
                    reservationDetail.setCheckInEstimate((reservationDetailDTO.getCheckInActual() != null) ? reservationDetailDTO.getCheckInActual() : reservationDetail.getCheckInActual());
                    reservationDetail.setCheckOutEstimate((reservationDetailDTO.getCheckOutActual() != null) ? reservationDetailDTO.getCheckOutActual() : reservationDetail.getCheckOutActual());
                }
                reservationDetail.setCheckInActual((reservationDetailDTO.getCheckInActual() != null) ? reservationDetailDTO.getCheckInActual() : reservationDetail.getCheckInActual());
                reservationDetail.setCheckOutActual((reservationDetailDTO.getCheckOutActual() != null) ? reservationDetailDTO.getCheckOutActual() : reservationDetail.getCheckOutActual());
                room.setBookingStatus(Status.ROOM_USING);
            }
            case Status.CHECK_OUT -> {
                reservationDetail.setCheckOutActual((reservationDetailDTO.getCheckOutActual() != null) ? reservationDetailDTO.getCheckOutActual() : reservationDetail.getCheckOutActual());
                room.setBookingStatus(Status.ROOM_EMPTY);
            }
        }

        reservationDetail.setPrice((reservationDetailDTO.getPrice() != null) ? reservationDetailDTO.getPrice() : reservationDetail.getPrice());
        reservationDetail.setReservationType((reservationDetailDTO.getReservationType() != null) ? reservationDetailDTO.getReservationType() : reservationDetail.getReservationType());
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
}
