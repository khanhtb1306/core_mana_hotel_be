package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Order;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    Order findTopByOrderByOrderIdDesc();

    List<Order> findByReservationDetail_ReservationDetailId(Long reservationDetailId);

    List<Order> findOrderByReservationDetail(ReservationDetail reservationDetail);

    List<Order> findOrderByReservationDetailAndStatus(ReservationDetail reservationDetail, String Status);

    @Query("SELECT o FROM Order o " +
            "LEFT JOIN OrderDetail od on o.orderId = od.order.orderId " +
            "WHERE o.reservationDetail.reservationDetailId = 0 " +
            "AND o.status = 'PAID' " +
            "ORDER BY o.createdDate DESC")
    List<Object[]> findAllRetailOrdersWithDetails();

}
