package com.manahotel.be.repository;

import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RoomClassRepository extends JpaRepository<RoomCategory, String> {

    RoomCategory findTopByOrderByRoomCategoryIdDesc();

    @Query("SELECT rc, COUNT(r) FROM RoomCategory rc " +
            "LEFT JOIN Room r ON rc.roomCategoryId = r.roomCategory.roomCategoryId " +
            "WHERE rc.status <> 6 " +
            "GROUP BY rc")
    List<Object[]> findRoomCategoriesWithRoomCount();

    @Query("SELECT rc FROM RoomCategory rc " +
            "LEFT JOIN Room r ON rc.roomCategoryId = r.roomCategory.roomCategoryId")
    List<Object[]> findRoomByRoomCategory();

    @Query(value = "SELECT rc " +
            "FROM RoomCategory rc " +
            "LEFT JOIN Room r ON rc.roomCategoryId = r.roomCategory.roomCategoryId " +
            "WHERE r.status = 1 AND r.roomId NOT IN " +
            "(SELECT r2.roomId FROM Room r2 " +
            "LEFT JOIN ReservationDetail rd ON r2.roomId = rd.room.roomId " +
            "LEFT JOIN Reservation re ON re.reservationId = rd.reservation.reservationId " +
            "WHERE (re.status NOT IN ('PENDING', 'DISCARD') " +
            "AND rd.status NOT IN ('CHECK_OUT') " +
            "AND (rd.checkInActual < ?2 OR (rd.checkInActual IS NULL AND rd.checkInEstimate < ?2)) " +
            "AND (rd.checkOutActual > ?1 OR (rd.checkOutActual IS NULL AND rd.checkOutEstimate > ?1))) " +
            "AND rd.reservationDetailStatus = 1 " +
            "GROUP BY r2.roomId) " +
            "GROUP BY rc.roomCategoryId " +
            "ORDER BY rc.roomCategoryId")
    List<RoomCategory> findEmptyRoomCategory(Timestamp startDate, Timestamp endDate);
}
