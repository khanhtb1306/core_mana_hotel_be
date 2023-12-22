package com.manahotel.be.service;

import com.manahotel.be.common.constant.Const;
import com.manahotel.be.model.dto.response.OrderDetailDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsUnitRepository goodsUnitRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private OrderRepository orderRepository;

    private void commonMapping(OrderDetail orderDetail, OrderDetailDTO orderDetailDTO) throws IOException {
        orderDetail.setQuantity(orderDetailDTO.getQuantity() != null ? orderDetailDTO.getQuantity() : orderDetail.getQuantity());
        orderDetail.setPrice(orderDetailDTO.getPrice() != null ? orderDetailDTO.getPrice() : orderDetail.getPrice());
    }
    public void createOrderDetail(OrderDetailDTO orderDetailDTO, String orderId, String invoiceId) {
        try {
            log.info("------- Add OrderDetail Start -------");
            OrderDetail orderDetail = new OrderDetail();
            commonMapping(orderDetail, orderDetailDTO);
            Goods goods = goodsRepository.findById(orderDetailDTO.getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found goods"));
            GoodsUnit goodsUnit = goodsUnitRepository.findById(orderDetailDTO.getGoodsUnitId()).orElseThrow(() -> new IllegalStateException("Not found goodsUnit"));
            Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new IllegalStateException("Not found invoice"));
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("order with id not exists"));
            orderDetail.setOrder(order);
            orderDetail.setGoods(goods);
            orderDetail.setGoodsUnit(goodsUnit);
            orderDetail.setInvoice(invoice);
            commonMapping(orderDetail, orderDetailDTO);
            if(orderId.equals(Const.ORDER_ID)){
                if(goods.isGoodsCategory()) {
                    goods.setInventory(goods.getInventory() - orderDetail.getQuantity());
                    goodsRepository.save(goods);
                }
            }
            orderDetailRepository.save(orderDetail);
            log.info("------- Add OrderDetail End -------");
        } catch (Exception e) {
            log.info("Can't Add OrderDetail", e.getMessage());
        }
    }
    public void updateOrderDetail(OrderDetail orderDetail)
    {
        Goods goods = goodsRepository.findById(orderDetail.getGoods().getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found goods"));
        if(goods.isGoodsCategory()) {
            goods.setInventory(goods.getInventory() - orderDetail.getQuantity());
            goodsRepository.save(goods);
        }
    }

    public void deleteOrderDetails(String orderId) {
        try {
            log.info("------- Delete OrderDetails Start -------");
            List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrder_OrderId(orderId);
            if(orderDetails.isEmpty()) {
                log.info("Can't Delete OrderDetails");
            }
                for (OrderDetail orderDetail : orderDetails) {
                    Goods goods = goodsRepository.findById(orderDetail.getGoods().getGoodsId()).orElseThrow(() -> new IllegalStateException("Not found goods"));
                    if(goods.isGoodsCategory()){
                        goods.setInventory(goods.getInventory() + orderDetail.getQuantity());
                        goodsRepository.save(goods);
                    }
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
