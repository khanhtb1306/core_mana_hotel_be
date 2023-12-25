package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Room findTopByOrderByRoomIdDesc();

    List<Room> findByRoomCategory(RoomCategory roomCategory);

    List<Room> findByFloor_FloorIdAndStatusIsNot(Long floorId, Long status);

    List<Room> findByRoomCategoryAndStatus(RoomCategory roomCategory, Long status);

    List<Room> findByStatusNot(Long status);

    List<Room> findByStatus(Long status);

    List<Room> findByFloor(Floor floor);
    Room findByRoomId(String roomId);

    @Query(value = "SELECT r " +
            "FROM Room r " +
            "LEFT JOIN RoomCategory rc ON rc.roomCategoryId = r.roomCategory.roomCategoryId " +
            "WHERE rc.status = 1 AND r.status = 1 AND r.roomId NOT IN " +
            "(SELECT r2.roomId FROM Room r2 " +
            "LEFT JOIN ReservationDetail rd ON r2.roomId = rd.room.roomId " +
            "LEFT JOIN Reservation re ON re.reservationId = rd.reservation.reservationId " +
            "WHERE (re.status NOT IN ('PENDING', 'DISCARD') " +
            "AND rd.status NOT IN ('CHECK_OUT', 'DONE') " +
            "AND rd.reservationDetailStatus = 1 " +
            "AND (rd.checkInActual < ?2 OR (rd.checkInActual IS NULL AND rd.checkInEstimate < ?2)) " +
            "AND (rd.checkOutActual > ?1 OR (rd.checkOutActual IS NULL AND rd.checkOutEstimate > ?1))) " +
            "GROUP BY r2.roomId) " +
            "AND rc.roomCategoryId = ?3 " +
            "ORDER BY r.roomId")
    List<Room> findEmptyRoom(Timestamp startDate, Timestamp endDate, String roomCategoryId);


    @Query(value = "SELECT " +
            "(SELECT COUNT(*) FROM room WHERE status = 1) as totalRooms, " +
            "(SELECT COUNT(*) FROM room WHERE booking_status = 'ROOM_USING' AND status = 1) as roomsInUse, " +
            "(SELECT COUNT(*) FROM room WHERE booking_status = 'ROOM_EMPTY' AND status = 1) as emptyRooms", nativeQuery = true)
    List<Object[]> getTotalAndEmptyRoomCounts();


}
