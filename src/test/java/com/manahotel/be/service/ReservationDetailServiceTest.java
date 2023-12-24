package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import com.manahotel.be.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReservationDetailServiceTest {

    @Mock
    private ReservationDetailRepository reservationDetailRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationDetailCustomerService reservationDetailCustomerService;

    @InjectMocks
    private ReservationDetailService reservationDetailService;

    @BeforeEach
    void setUp() {
        Mockito.reset(reservationDetailRepository, reservationRepository, roomRepository, reservationDetailCustomerService);
    }

    @Test
    void createReservationDetail() {
        // Mock data
        ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO();
        reservationDetailDTO.setReservationId("1");
        reservationDetailDTO.setRoomId("2");
        reservationDetailDTO.setStatus(Status.BOOKING);

        // Mock repository behavior
        when(reservationRepository.findById("1")).thenReturn(Optional.of(new Reservation()));
        when(roomRepository.findById("2")).thenReturn(Optional.of(new Room()));
        when(reservationDetailRepository.save(any(ReservationDetail.class))).thenReturn(new ReservationDetail());

        // Call the service method
        ResponseDTO responseDTO = reservationDetailService.createReservationDetail(reservationDetailDTO);

        // Verify the results
        assertEquals("Thêm phòng null thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void updateReservationDetail() {
        // Mock data
        Long reservationDetailId = 1L;
        ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO();
        reservationDetailDTO.setReservationId("1");
        reservationDetailDTO.setRoomId("2");
        reservationDetailDTO.setStatus(Status.BOOKING);

        ReservationDetail existingReservationDetail = new ReservationDetail();
        existingReservationDetail.setReservationDetailId(reservationDetailId);

        // Mock repository behavior
        when(reservationDetailRepository.findById(reservationDetailId)).thenReturn(Optional.of(existingReservationDetail));
        when(reservationRepository.findById("1")).thenReturn(Optional.of(new Reservation()));
        when(roomRepository.findById("2")).thenReturn(Optional.of(new Room()));
        when(reservationDetailRepository.save(any(ReservationDetail.class))).thenReturn(existingReservationDetail);

        // Call the service method
        ResponseDTO responseDTO = reservationDetailService.updateReservationDetail(reservationDetailId, reservationDetailDTO);

        // Verify the results
        assertEquals("Cập nhật chi tiết đặt phòng thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteReservationDetail() {
        // Mock data
        Long reservationDetailId = 1L;

        // Mock repository behavior
        when(reservationDetailRepository.findById(reservationDetailId)).thenReturn(Optional.of(new ReservationDetail()));

        // Call the service method
        ResponseDTO responseDTO = reservationDetailService.deleteReservationDetail(reservationDetailId);

        // Verify the results
        assertTrue(responseDTO.isSuccess());
        assertEquals("Xóa chi tiết đặt phòng thành công", responseDTO.getDisplayMessage());
        verify(reservationDetailRepository).save(any(ReservationDetail.class));
    }
}