package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.BookingConflictException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.exception.RoomInUseException;
import com.manahotel.be.model.dto.request.ListTimePrice;
import com.manahotel.be.model.dto.response.ListTimePriceResponse;
import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

            return ResponseUtils.success(reservationDetail, "Thêm phòng " + reservationDetail.getRoom().getRoomName() + " thành công");
        } catch (Exception e) {
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
        } catch (Exception e) {
            log.info("----- Update detail for reservation failed ------\n" + e.getMessage());
            return ResponseUtils.error(findReservationDetail(id).getReservationDetailId(), e.getMessage());
        }
    }

    public ResponseDTO changeRoomInReservation(String reservationId, String roomId, ReservationDetailDTO reservationDetailDTO) {
        try {
            log.info("----- Start changing room -----");
            ReservationDetail reservationDetail = repository.findReservationDetailByReservationAndRoom(findReservation(reservationId), findRoom(roomId));

            Room oldRoom = reservationDetail.getRoom();
            Room newRoom = (reservationDetailDTO.getRoomId() != null) ? findRoom(reservationDetailDTO.getRoomId()) : reservationDetail.getRoom();

            if (reservationDetail.getStatus().equals(Status.CHECK_IN)) {
                if (newRoom.getBookingStatus().equals(Status.ROOM_USING) && !repository.existInReservationDetail(reservationDetail)) {
                    throw new RoomInUseException("Phòng " + newRoom.getRoomName() + " đang được sử dụng, không thể nhận phòng");
                }

                reservationDetail.setPrice((reservationDetailDTO.getPrice() != null) ? reservationDetailDTO.getPrice() : reservationDetail.getPrice());

                oldRoom.setBookingStatus(Status.ROOM_EMPTY);
                repository3.save(oldRoom);

                newRoom.setBookingStatus(Status.ROOM_USING);
                repository3.save(newRoom);
            }

            reservationDetail.setRoom(newRoom);

            repository.save(reservationDetail);
            log.info("----- End changing room -----");

            return ResponseUtils.success("Thay đổi phòng thành công");
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        reservationDetail.setReservationType((reservationDetailDTO.getReservationType() != null) ? reservationDetailDTO.getReservationType() : reservationDetail.getReservationType());

        switch (reservationDetail.getStatus()) {
            case Status.BOOKING -> {
                reservationDetail.setCheckInEstimate((reservationDetailDTO.getCheckInEstimate() != null) ? reservationDetailDTO.getCheckInEstimate() : reservationDetail.getCheckInEstimate());
                reservationDetail.setCheckOutEstimate((reservationDetailDTO.getCheckOutEstimate() != null) ? reservationDetailDTO.getCheckOutEstimate() : reservationDetail.getCheckOutEstimate());

                checkDuplicateBooking(reservationDetail.getCheckInEstimate(), reservationDetail.getCheckOutEstimate(), reservationDetail.getRoom(), reservationDetail.getReservationDetailId());
            }
            case Status.CHECK_IN -> {
                if(reservationDetail.getCheckInEstimate() != null) {
                    reservationDetail.setCheckInEstimate((reservationDetailDTO.getCheckInActual() != null) ? reservationDetailDTO.getCheckInActual() : reservationDetail.getCheckInActual());
                }

                reservationDetail.setCheckInActual((reservationDetailDTO.getCheckInActual() != null) ? reservationDetailDTO.getCheckInActual() : reservationDetail.getCheckInActual());
                reservationDetail.setCheckOutEstimate((reservationDetailDTO.getCheckOutEstimate() != null) ? reservationDetailDTO.getCheckOutEstimate() : reservationDetail.getCheckOutEstimate());

                if (room.getBookingStatus().equals(Status.ROOM_USING) && !repository.existInReservationDetail(reservationDetail)) {
                    throw new RoomInUseException("Phòng " + room.getRoomName() + " đang được sử dụng, không thể nhận phòng");
                }

                checkDuplicateBooking(reservationDetail.getCheckInActual(), reservationDetail.getCheckOutEstimate(), reservationDetail.getRoom(), reservationDetail.getReservationDetailId());

                room.setBookingStatus(Status.ROOM_USING);
            }
            case Status.CHECK_OUT -> {
                reservationDetail.setCheckOutActual((reservationDetailDTO.getCheckOutActual() != null) ? reservationDetailDTO.getCheckOutActual() : reservationDetail.getCheckOutActual());

                checkDuplicateBooking(reservationDetail.getCheckInActual(), reservationDetail.getCheckOutActual(), reservationDetail.getRoom(), reservationDetail.getReservationDetailId());

                room.setConditionStatus(Status.UNCLEAN);
                room.setBookingStatus(Status.ROOM_EMPTY);
            }
        }

        repository3.save(room);

        reservationDetail.setPrice((reservationDetailDTO.getPrice() != null) ? reservationDetailDTO.getPrice() : reservationDetail.getPrice());

        repository.save(reservationDetail);

        Timestamp start = repository2.findMinCheckIn(reservation);
        Timestamp end = repository2.findMaxCheckOut(reservation);

        reservation.setDurationStart(start);
        reservation.setDurationEnd(end);

        repository2.save(reservation);
    }

    public ResponseDTO updatePriceHistoryOverTime(List<ListTimePrice> timePrices, Long reservationDetailId) {
        log.info("----- Update Price History Over Time Start -----");
        try {
            if (timePrices.isEmpty()) {
                return ResponseUtils.error("priceHistoryOverTime_isFail");
            }
            StringBuilder result = new StringBuilder();
            for (ListTimePrice ltp : timePrices) {
                LocalDate date = LocalDate.parse(ltp.getTime(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                String timePrice = date + ":" + ltp.getPrice();
                result.append(timePrice).append(";");
            }

            ReservationDetail reservationDetail = findReservationDetail(reservationDetailId);
            if (!reservationDetail.getPriceHistoryOverTime().isEmpty()) {
                if(reservationDetail.getPriceHistoryOverTime().equals(result.toString())){
                    reservationDetail.setPriceHistoryOverTime(result.toString());
                    repository.save(reservationDetail);
                }
            } else {
                reservationDetail.setPriceHistoryOverTime(result.toString());
                repository.save(reservationDetail);
            }

            log.info("----- Update Price History Over Time End -----");
            return ResponseUtils.success("priceHistoryOverTime_isSuccess");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.error("priceHistoryOverTime_isFail");
        }
    }

    public ResponseDTO GetPriceHistoryOverTime(String reservationId) {
        log.info("----- Get Price History Over Time Start -----");
        try {
            List<ReservationDetail> reservationDetails = repository.findReservationDetailByReservation_ReservationId(reservationId);
            List<Object> listPriceHistoryOverTimeByReservation = new ArrayList<>();
            for(ReservationDetail rd: reservationDetails) {
                List<ListTimePriceResponse> listTimePrices = new ArrayList<>();
                Map<String, Object> mapInfo = new HashMap<>();
                if(rd.getPriceHistoryOverTime() != null){
                    String[] timePriceArray = rd.getPriceHistoryOverTime().split(";");
                    for (String tp : timePriceArray) {
                        String[] parts = tp.split(":");
                        if (parts.length == 2) {
                            LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                            float price = Float.parseFloat(parts[1]);
                            ListTimePriceResponse listTimePrice = new ListTimePriceResponse();
                            listTimePrice.setTime(date);
                            listTimePrice.setPrice(price);
                            listTimePrices.add(listTimePrice);
                        }
                    }
                    mapInfo.put("ReservationDetail", rd);
                    mapInfo.put("PriceHistoryOverTime", rd);
                }
                listPriceHistoryOverTimeByReservation.add(mapInfo);
            }
            log.info("----- Get Price History Over Time End -----");
            return ResponseUtils.success(listPriceHistoryOverTimeByReservation, "priceHistoryOverTime_isSuccess");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("priceHistoryOverTime_isFail");

        }
    }

    public ResponseDTO checkDuplicateBooking(Timestamp start, Timestamp end, String roomId, Long reservationDetailId) {
        try {
            log.info("----- Start check duplicate booking -----");
            checkDuplicateBooking(start, end, findRoom(roomId), reservationDetailId);
            log.info("----- End check duplicate booking -----");

            return ResponseUtils.success("Không có lịch bị trùng với phòng " + findRoom(roomId).getRoomName());
        }
        catch (Exception e) {
            log.info("----- Check duplicate booking failed -----\n" + e.getMessage());
            return ResponseUtils.error("Lịch phòng " + findRoom(roomId).getRoomName() + " đang trùng với các lịch khác");
        }
    }

    public ResponseDTO getReservationDetailByDate(Timestamp start, Timestamp end) {
        return ResponseUtils.success(repository.findByDate(start, end), "Hiển thị danh sách thành công");
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

        if (!listReservationDetails.isEmpty()) {
            throw new BookingConflictException("Lịch phòng " + room.getRoomName() + " đang trùng với các lịch khác");
        }
    }
}
