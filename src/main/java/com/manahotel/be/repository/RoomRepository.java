package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Room findTopByOrderByRoomIdDesc();
}
