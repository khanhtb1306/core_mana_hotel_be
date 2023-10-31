package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.RoomDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.OrderDetailRepository;
import com.manahotel.be.repository.OrderRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.StaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private StaffRepository staffRepository;

    private void commonMapping(Order order, OrderDTO orderDTO) throws IOException {
        order.setTotalPay(orderDTO.getTotalPay() != null ? orderDTO.getTotalPay() : order.getTotalPay());
        order.setStatus(orderDTO.getStatus() != null ? orderDTO.getStatus() : order.getStatus());
    }

    public ResponseDTO createOrder(OrderDTO orderDTO){
        try {
            log.info("------- Add Order Start -------");
            Order latestOrder = orderRepository.findTopByOrderByOrderIdDesc();
            String latestId = (latestOrder == null) ? null : latestOrder.getOrderId();
            String nextId = IdGenerator.generateId(latestId, "Or");
            Order order = new Order();
            order.setOrderId(nextId);
            order.setCreatedById(findStaff().getStaffId());
            order.setReservationDetail(findReservationDetail(orderDTO.getReservationDetailId()));

            commonMapping(order, orderDTO);

            orderRepository.save(order);

            log.info("------- Add Order End -------");
            return ResponseUtils.success("Thêm hóa đơn thành công");

        }catch (Exception e){
            log.info("Can't Add Order", e.getMessage());
            return ResponseUtils.error("Thêm hóa đơn thất bại");

        }
    }
    public Staff findStaff(){
        String staffName = SecurityContextHolder.getContext().getAuthentication().getName();
        return staffRepository.findByUsername(staffName);
    }
    public ReservationDetail findReservationDetail(Long reservationDetailId)
    {
        return reservationDetailRepository.findById(reservationDetailId).orElseThrow(() -> new IllegalStateException("customer with id "  + " not exists"));
    }

    public ResponseDTO updateTotalPayOrder(Long reservationDetailId){
        try{
            log.info("------- Update Order Start -------");

            Order latestOrder = orderRepository.findByReservationDetail_ReservationDetailId(reservationDetailId);
            float total = 0;
            List<OrderDetail> orderDetailList = orderDetailRepository.findByOrder_OrderId(latestOrder.getOrderId());
            for (OrderDetail orderDetail : orderDetailList){
                total += (orderDetail.getPrice() * orderDetail.getQuantity());
            }
            latestOrder.setTotalPay(total);
            latestOrder.setCreatedDate(Instant.now());

            orderRepository.save(latestOrder);

            log.info("------- Update Order End -------");
            return ResponseUtils.success("Cập nhật hóa đơn thành công");

        }catch (Exception e){
            log.info("Can't Update Order", e.getMessage());
            return ResponseUtils.error("Cập nhật hóa đơn thất bại");
        }
    }
}
