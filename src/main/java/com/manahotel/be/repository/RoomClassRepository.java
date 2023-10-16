package com.manahotel.be.repository;

import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomClassRepository extends JpaRepository<RoomCategory, String> {
    RoomCategory findTopByOrderByRoomCategoryIdDesc();

    @Query("SELECT rc, COUNT(r) FROM RoomCategory rc " +
            "LEFT JOIN Room r ON rc.roomCategoryId = r.roomCategory.roomCategoryId " +
            "GROUP BY rc")
    List<Object[]> findRoomCategoriesWithRoomCount();
}
