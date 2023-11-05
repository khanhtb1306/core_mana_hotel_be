package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.OrderDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
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

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private GoodsRepository goodsRepository;

//    private void commonMapping(Order order, OrderDTO orderDTO) throws IOException {
//        order.setTotalPay(orderDTO.getTotalPay() != null ? orderDTO.getTotalPay() : order.getTotalPay());
//        order.setStatus(orderDTO.getStatus() != null ? orderDTO.getStatus() : order.getStatus());
//    }
    private void commonMapping(OrderDetail orderDetail, OrderDetailDTO orderDetailDTO) throws IOException {
        orderDetail.setQuantity(orderDetailDTO.getQuantity() != null ? orderDetailDTO.getQuantity() : orderDetail.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice() != null ? orderDetailDTO.getPrice() : orderDetail.getPrice());
    }

    public ResponseDTO createOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTOList) {
        try {
            log.info("------- Add Order Start -------");
            Order latestOrder = orderRepository.findTopByOrderByOrderIdDesc();
            String latestId = (latestOrder == null) ? null : latestOrder.getOrderId();
            String nextId = IdGenerator.generateId(latestId, "OR");

            Order order = new Order();
            order.setOrderId(nextId);
            ReservationDetail reservationDetail = reservationDetailRepository.findById(orderDTO.getReservationDetailId()).orElseThrow(() -> new IllegalStateException("reservation with id not exists"));
            order.setReservationDetail(reservationDetail);
            order.setCreatedById(findStaff().getStaffId());

            List<OrderDetail> orderDetailList = new ArrayList<>();

            OrderDetail orderDetail = new OrderDetail();
            for(OrderDetailDTO orderDetailDTO1: orderDetailDTOList){
                commonMapping(orderDetail, orderDetailDTO1);
                Goods goods = goodsRepository.findById(orderDetailDTO1.getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found goods"));
                orderDetail.setOrder(order);
                orderDetail.setGoods(goods);
                commonMapping(orderDetail, orderDetailDTO1);
                orderDetailList.add(orderDetail);
            }
            float total = 0;
            for (OrderDetail orderDetail1 : orderDetailList) {
                total += (orderDetail1.getPrice() * orderDetail1.getQuantity());
            }

            order.setTotalPay(total);
            order.setCreatedDate(Instant.now());
            orderRepository.save(order);

            for (OrderDetail orderDetail1 : orderDetailList) {
                orderDetailRepository.save(orderDetail1);
            }
            log.info("------- Add Order End -------");
            return ResponseUtils.success("Thêm hóa đơn thành công");

        } catch (Exception e) {
            log.info("Can't Add Order", e.getMessage());
            return ResponseUtils.error("Thêm hóa đơn thất bại");

        }
    }

    public Staff findStaff() {
        try {
            String staffName = SecurityContextHolder.getContext().getAuthentication().getName();
            return staffRepository.findByUsername(staffName);
        } catch (Exception e) {
            log.error("Failed to find staff" + e.getMessage());
            return null;
        }
    }
}
