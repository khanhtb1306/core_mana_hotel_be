package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findByStatusNot(Long status);

    @Query("SELECT f FROM Floor f " +
            "LEFT JOIN Room r ON f.floorId = r.floor.floorId")
    List<Object[]> findRoomByFloor();
}