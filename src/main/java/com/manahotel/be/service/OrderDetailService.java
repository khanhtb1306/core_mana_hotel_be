package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.OrderDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.Order;
import com.manahotel.be.model.entity.OrderDetail;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.OrderDetailRepository;
import com.manahotel.be.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    private void commonMapping(OrderDetail orderDetail, OrderDetailDTO orderDetailDTO) throws IOException {
        orderDetail.setQuantity(orderDetailDTO.getQuantity() != null ? orderDetailDTO.getQuantity() : orderDetail.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice() != null ? orderDetailDTO.getPrice() : orderDetail.getPrice());
    }
    public void createOrderDetail(OrderDetailDTO orderDetailDTO, Order order) {
        try {
            log.info("------- Add OrderDetail Start -------");

            OrderDetail orderDetail = new OrderDetail();
            commonMapping(orderDetail, orderDetailDTO);
            Goods goods = goodsRepository.findById(orderDetailDTO.getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found goods"));
            orderDetail.setOrder(order);
            orderDetail.setGoods(goods);
            commonMapping(orderDetail, orderDetailDTO);
            goods.setInventory(goods.getInventory() - orderDetail.getQuantity());
            goodsRepository.save(goods);
            orderDetailRepository.save(orderDetail);
            log.info("------- Add OrderDetail End -------");
        } catch (Exception e) {
            log.info("Can't Add OrderDetail", e.getMessage());
        }
    }


    public void deleteOrderDetails(String orderId) {
        try {
            log.info("------- Delete OrderDetails Start -------");
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_OrderId(orderId);
            if(orderDetails.isEmpty()) {
                log.info("Can't Delete OrderDetails");
            }
            for (OrderDetail orderDetail : orderDetails) {
                Goods goods = goodsRepository.findById(orderDetail.getGoods().getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found goods"));
                goods.setInventory(goods.getInventory() + orderDetail.getQuantity());
                goodsRepository.save(goods);
            }
            orderDetailRepository.deleteAll(orderDetails);
            log.info("------- Delete OrderDetails End -------");
        } catch (IllegalStateException ei) {
            log.info(ei.getMessage());
        } catch (Exception e) {
            log.info("Can't Delete OrderDetails", e.getMessage());
        }
    }
}
