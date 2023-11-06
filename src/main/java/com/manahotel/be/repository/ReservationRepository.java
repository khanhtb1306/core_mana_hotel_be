package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("SELECT r FROM Reservation r " +
            "LEFT JOIN ReservationDetail rd ON r.reservationId = rd.reservation.reservationId")
    List<Object[]> findReservationsWithRooms();

    Reservation findTopByOrderByReservationIdDesc();
}
