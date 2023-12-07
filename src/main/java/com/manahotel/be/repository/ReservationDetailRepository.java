package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {

    List<ReservationDetail> findReservationDetailByReservationAndReservationDetailStatus(Reservation reservation, Long status);

    List<ReservationDetail> findReservationDetailByRoomAndReservationDetailStatus(Room room, Long status);

    ReservationDetail findReservationDetailByReservationAndRoom(Reservation reservation, Room room);

    @Query("SELECT rd FROM ReservationDetail rd " +
            "WHERE rd.reservation.reservationId = ?1 AND rd.reservationDetailStatus <> 6")
    List<ReservationDetail> findReservationDetailByReservation_ReservationId(String reservationId);

    @Query("SELECT rd FROM ReservationDetail rd " +
            "WHERE rd.room.roomId = ?1 AND (rd.status = 'BOOKING' OR rd.status = 'CHECK_IN')")
    List<ReservationDetail> findBookingAndCheckInDetailsByRoomId(String roomId);

    @Query("SELECT rd FROM ReservationDetail rd " +
            "WHERE rd.room.roomId = ?1  AND rd.status <> 'BOOKING'" +
            "AND rd.reservationDetailStatus = 1 " +
            "AND ((rd.status = 'CHECK_IN') OR (rd.status = 'CHECK_OUT' AND DATE(rd.checkOutActual) = CURRENT_DATE))")
    List<ReservationDetail> checkRoomCapacityDaily(String roomId);



    @Query(value = "SELECT rd FROM ReservationDetail rd " +
            "LEFT JOIN Reservation r on r.reservationId = rd.reservation.reservationId " +
            "WHERE rd.room.status = 1 " +
            "AND r.status NOT IN ('PENDING', 'DISCARD') " +
            "AND rd.status NOT IN ('CHECK_OUT', 'DONE') " +
            "AND (rd.checkInActual < ?2 OR (rd.checkInActual IS NULL AND rd.checkInEstimate < ?2)) " +
            "AND (rd.checkOutActual > ?1 OR (rd.checkOutActual IS NULL AND rd.checkOutEstimate > ?1)) " +
            "AND rd.reservationDetailStatus = 1 " +
            "AND rd.room = ?3 " +
            "AND (?4 IS NULL OR rd.reservationDetailId <> ?4)")
    List<ReservationDetail> checkBooking(Timestamp startDate, Timestamp endDate, Room room, Long reservationDetailId);

    @Query(value = "SELECT rd FROM ReservationDetail rd " +
            "WHERE rd.reservationDetailStatus = 1 " +
            "AND (rd.checkInActual <= ?1 OR (rd.checkInActual IS NULL AND rd.checkInEstimate <= ?1)) " +
            "AND (rd.checkOutActual >= ?2 OR (rd.checkOutActual IS NULL AND rd.checkOutEstimate >= ?2)) " +
            "AND rd.room.status = 1")
    List<ReservationDetail> findByDate(Timestamp start, Timestamp end);

    @Query(value = "UPDATE reservation_detail SET reservation_detail_status = 6 WHERE reservation_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteReservationDetailByReservationId(String reservationId);

    @Query("SELECT COUNT(rd) > 0 " +
            "FROM ReservationDetail rd " +
            "WHERE rd.status = 'CHECK_IN' AND " +
            " rd = ?1")
    boolean existInReservationDetail(ReservationDetail reservationDetail);

    @Query("SELECT rd FROM ReservationDetail rd WHERE rd.status IN ('BOOKING', 'CHECK_IN') " +
            "AND rd.reservationDetailStatus = 1 " +
            "AND ((rd.status = 'BOOKING' AND rd.checkInEstimate <= ?1 AND rd.checkOutEstimate >= ?1) " +
            "OR (rd.status = 'CHECK_IN' AND rd.checkInActual <= ?1 AND rd.checkOutEstimate >= ?1)) " +
            "ORDER BY rd.status DESC")
    List<ReservationDetail> getReservationDetailByBookingAndCheckIn(Timestamp date);
}
