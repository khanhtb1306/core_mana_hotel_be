package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {

    List<ReservationDetail> findReservationDetailByReservationAndReservationDetailStatus(Reservation reservation, Long status);

    List<ReservationDetail> findReservationDetailByRoomAndReservationDetailStatus(Room room, Long status);

    ReservationDetail findReservationDetailByReservationAndRoom(Reservation reservation, Room room);

    @Query("SELECT rd FROM ReservationDetail rd " +
            "WHERE rd.room.roomId = ?1 " +
            "AND rd.reservationDetailStatus = 1 " +
            "AND ((rd.status = 'CHECK_IN') OR (rd.status = 'CHECK_OUT' AND DATE(rd.checkOutActual) = CURRENT_DATE))")
    List<ReservationDetail> checkRoomCapacityDaily(String roomId);



    @Query(value = "SELECT rd FROM ReservationDetail rd " +
            "LEFT JOIN Reservation r on r.reservationId = rd.reservation.reservationId " +
            "WHERE rd.room.status = 1 " +
            "AND r.status NOT IN ('PENDING', 'DISCARD') " +
            "and rd.status NOT IN ('CHECK_OUT') " +
            "AND (rd.checkInActual < ?2 OR (rd.checkInActual IS NULL AND rd.checkInEstimate < ?2)) " +
            "AND (rd.checkOutActual > ?1 OR (rd.checkOutActual IS NULL AND rd.checkOutEstimate > ?1)) " +
            "AND rd.reservationDetailStatus = 1")
    List<ReservationDetail> checkBooking(Timestamp startDate, Timestamp endDate);

    @Query(value = "UPDATE reservation_detail SET reservation_detail_status = 6 WHERE reservation_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteReservationDetailByReservationId(String reservationId);
}
