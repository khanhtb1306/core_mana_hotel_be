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
}
