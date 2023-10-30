package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.OrderDetailDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.Order;
import com.manahotel.be.model.entity.OrderDetail;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.OrderDetailRepository;
import com.manahotel.be.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private OrderRepository orderRepository;

    private void commonMapping(OrderDetail orderDetail, OrderDetailDTO orderDetailDTO) throws IOException {
        orderDetail.setQuantity(orderDetailDTO.getQuantity() != null ? orderDetailDTO.getQuantity() : orderDetail.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice() != null ? orderDetailDTO.getPrice() : orderDetail.getPrice());
    }

    public ResponseEntity<String> createOrderDetail(OrderDetailDTO orderDetailDTO) {
        try {
            log.info("------- Add OrderDetail Start -------");

            OrderDetail orderDetail = new OrderDetail();
            commonMapping(orderDetail, orderDetailDTO);
            Goods goods = goodsRepository.findById(orderDetailDTO.getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found"));
            Order order = orderRepository.findById(orderDetailDTO.getOrderId()).orElseThrow(() -> new IllegalStateException("Not found"));

            orderDetail.setOrder(order);
            orderDetail.setGoods(goods);

            commonMapping(orderDetail, orderDetailDTO);

            orderDetailRepository.save(orderDetail);
            log.info("------- Add OrderDetail End -------");
            return new ResponseEntity<>("Thêm chi tiết hóa đơn thành công", HttpStatus.OK);

        } catch (Exception e) {
            log.info("Can't Add OrderDetail", e.getMessage());
            return new ResponseEntity<>("Thêm chi tiết hóa đơn Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
