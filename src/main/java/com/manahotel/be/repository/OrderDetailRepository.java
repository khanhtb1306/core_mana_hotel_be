package com.manahotel.be.repository;

import com.manahotel.be.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findByOrder_OrderId(String orderId);
    void deleteByOrder_OrderId(String orderId);
}
