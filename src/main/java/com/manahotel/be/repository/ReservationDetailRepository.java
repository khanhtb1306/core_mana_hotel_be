package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {

    List<ReservationDetail> findReservationDetailByReservationAndReservationDetailStatus(Reservation reservation, Long status);

    List<ReservationDetail> findReservationDetailByRoomAndReservationDetailStatus(Room room, Long status);

    ReservationDetail findReservationDetailByReservationAndRoom(Reservation reservation, Room room);
}
