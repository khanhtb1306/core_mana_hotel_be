package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.OrderDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.OrderDetailRepository;
import com.manahotel.be.repository.OrderRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.StaffRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private OrderDetailService orderDetailService;
    private OrderDTO commonMappingResponseOrder(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setReservationDetailId(order.getReservationDetail().getReservationDetailId() );
        orderDTO.setTotalPay(order.getTotalPay());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setCreatedById(order.getCreatedById());
        orderDTO.setCreatedDate(order.getCreatedDate());
        return orderDTO;
    }

    public ResponseDTO createOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTOList) {
        try {
            log.info("------- Add Order Start -------");
            Order latestOrder = orderRepository.findTopByOrderByOrderIdDesc();
            String latestId = (latestOrder == null) ? null : latestOrder.getOrderId();
            String nextId = IdGenerator.generateId(latestId, "HD");
            Order order = new Order();
            order.setOrderId(nextId);

            ReservationDetail reservationDetail = reservationDetailRepository.findById(orderDTO.getReservationDetailId()).orElseThrow(() -> new IllegalStateException("reservation with id not exists"));
            order.setReservationDetail(reservationDetail);
            order.setCreatedById(findStaff().getStaffId());

            order.setTotalPay(totalPay(orderDetailDTOList));
            order.setCreatedDate(Instant.now());
            order.setStatus(Status.UNCONFIMRED);
            orderRepository.save(order);

            for (OrderDetailDTO orderDetail : orderDetailDTOList) {
                orderDetailService.createOrderDetail(orderDetail, order);
            }
            log.info("------- Add Order End -------");
            return ResponseUtils.success("Thêm hóa đơn thành công");

        } catch (Exception e) {
            log.info("Can't Add Order", e.getMessage());
            return ResponseUtils.error("Thêm hóa đơn thất bại");

        }
    }

    public ResponseDTO updateOrder(String orderId,List<OrderDetailDTO> orderDetailDTOList) {
        log.info("------- Update Order Start -------");

        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("order with id not exists"));

            orderDetailService.deleteOrderDetails(orderId);
            order.setTotalPay(totalPay(orderDetailDTOList));
            order.setCreatedDate(Instant.now());
            for (OrderDetailDTO orderDetail : orderDetailDTOList) {
                orderDetailService.createOrderDetail(orderDetail, order);
            }
            order.setStatus(Status.UNCONFIMRED);
            orderRepository.save(order);
            log.info("------- Update Order End -------");
            return ResponseUtils.success("Cập nhật hóa đơn thành công");
        }catch (Exception e){
            log.info("Can't Update Order", e.getMessage());
            return ResponseUtils.error("Cập nhật hóa đơn thất bại");
        }
    }

    public ResponseDTO deleteOrder(String orderId){
        log.info("------- Delete Order End -------");

        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("order with id not exists"));
            order.setStatus(Status.CANCEL_ORDER);
            orderDetailService.deleteOrderDetails(order.getOrderId());
            orderRepository.save(order);
            log.info("------- Delete Order End -------");
            return ResponseUtils.success("Xoá hóa đơn thành công");
        }catch (Exception e){
            log.info("Can't Delete Order", e.getMessage());
            return ResponseUtils.error("Xóa hóa đơn thất bại");
        }
    }
    public ResponseDTO updateStatusOrder(String orderId,String status){

        log.info("------- Update Status Order End -------");
        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("order with id not exists"));
            if(status.toUpperCase().equals(Status.UNCONFIMRED)){
                order.setStatus(Status.UNCONFIMRED);
            }
            else if(status.toUpperCase().equals(Status.CONFIMRED)){
                order.setStatus(Status.CONFIMRED);
            }
            else if(status.toUpperCase().equals(Status.PAID)){
                order.setStatus(Status.PAID);
            }
            else if(status.toUpperCase().equals(Status.CANCEL_ORDER)){
                order.setStatus(Status.CANCEL_ORDER);
            }
            else{
                return ResponseUtils.error("Cập nhật trạng thái hóa đơn thất bại");
            }
            orderRepository.save(order);
            log.info("------- Update Status Order End -------");
            return ResponseUtils.success("Cập nhật trạng thái hóa đơn thành công");
        }catch (Exception e){
            log.info("Can't Update Status Order", e.getMessage());
            return ResponseUtils.error("Cập nhật trạng thái hóa đơn thất bại");
        }
    }
    public ResponseDTO getOrderByReservationDetailId(Long reservationId){
        log.info("------- Get Order Start -------");
        List<Map<String, Object>> result = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO();
        try{
            List<Order> orderList = orderRepository.findByReservationDetail_ReservationDetailId(reservationId);
            for (Order order : orderList)
            {
                Map<String, Object> orderInfo = new HashMap<>();
                orderInfo.put("order", commonMappingResponseOrder(order));
                result.add(orderInfo);
            }
            log.info("------- Get Order End -------");
            return ResponseUtils.success(result,"Lấy hóa đơn thành công");

        }catch (Exception e){
            log.info("Can't Get Order", e.getMessage());
            return ResponseUtils.error("Lấy hóa đơn thất bại");
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

    public Float totalPay(List<OrderDetailDTO> orderDetailDTOList) {
        float total = 0;
        try {
            for (OrderDetailDTO orderDetail : orderDetailDTOList) {
                total += (orderDetail.getPrice() * orderDetail.getQuantity());
            }
            return total;
        } catch (Exception e) {
            log.error("Failed to find order");
            return null;
        }
    }
}