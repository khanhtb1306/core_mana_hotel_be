package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Query("SELECT r FROM Reservation r " +
            "LEFT JOIN ReservationDetail rd ON r.reservationId = rd.reservation.reservationId")
    List<Object[]> findReservationsWithRooms();

    Reservation findTopByOrderByReservationIdDesc();

    @Query(value = "SELECT r " +
            "FROM Room r " +
            "LEFT JOIN RoomCategory rc ON rc.roomCategoryId = r.roomCategory.roomCategoryId " +
            "LEFT JOIN ReservationDetail rd ON rd.room.roomId = r.roomId " +
            "LEFT JOIN Reservation re ON re.reservationId = rd.reservation.reservationId " +
            "WHERE r.roomId NOT IN (SELECT rd2.room.roomId FROM ReservationDetail rd2) " +
            "OR re.status IN ('2') " +
            "OR (rd.checkInActual > ?2 " +
            "    OR (rd.checkInActual IS NULL AND rd.checkInEstimate > ?2) " +
            "    OR rd.checkOutEstimate < ?1) " +
            "ORDER BY rc.roomCategoryName")
    List<Room> findAllEmptyRoomByReservation(Timestamp startDate, Timestamp endDate);
}
