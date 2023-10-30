package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ReservationDetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDetailRoomRepository extends JpaRepository<ReservationDetailRoom, Long> {
}
