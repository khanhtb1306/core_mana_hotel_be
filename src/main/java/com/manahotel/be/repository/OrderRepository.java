package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Order;
import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    Order findTopByOrderByOrderIdDesc();

    Order findByReservationDetail_Id(Long reservationDetailId);



}
