package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("SELECT r FROM Reservation r " +
            "LEFT JOIN ReservationDetail rd ON r.reservationId = rd.reservation.reservationId")
    List<Object[]> findReservationsWithRooms();

    Reservation findTopByOrderByReservationIdDesc();

    @Query("SELECT MIN(CASE WHEN rd.checkInActual IS NULL THEN rd.checkInEstimate ELSE rd.checkInActual END) FROM ReservationDetail rd " +
            "WHERE rd.reservation = ?1 AND rd.reservationDetailStatus <> 6")
    Timestamp findMinCheckIn(Reservation reservation);

    @Query("SELECT MAX(CASE WHEN rd.checkOutActual IS NULL THEN rd.checkOutEstimate ELSE rd.checkOutActual END) FROM ReservationDetail rd " +
            "WHERE rd.reservation = ?1 AND rd.reservationDetailStatus <> 6")
    Timestamp findMaxCheckOut(Reservation reservation);
}
