package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {

    List<ReservationDetail> findReservationDetailByReservationAndReservationDetailStatus(Reservation reservation, Long status);

    List<ReservationDetail> findReservationDetailByRoomAndReservationDetailStatus(Room room, Long status);

    ReservationDetail findReservationDetailByReservationAndRoom(Reservation reservation, Room room);

    @Query(value = "SELECT rd FROM ReservationDetail rd " +
            "LEFT JOIN Reservation r on r.reservationId = rd.reservation.reservationId " +
            "WHERE rd.room.status = 1 " +
            "AND r.status NOT IN ('PENDING', 'DISCARD') " +
            "and rd.status NOT IN ('CHECK_OUT') " +
            "AND (rd.checkInActual < ?2 OR (rd.checkInActual IS NULL AND rd.checkInEstimate < ?2)) " +
            "AND (rd.checkOutActual > ?1 OR (rd.checkOutActual IS NULL AND rd.checkOutEstimate > ?1)) " +
            "AND rd.reservationDetailStatus = 1")
    List<ReservationDetail> checkBooking(Timestamp startDate, Timestamp endDate);
}
