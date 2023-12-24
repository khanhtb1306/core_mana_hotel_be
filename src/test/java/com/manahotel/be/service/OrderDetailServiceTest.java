package com.manahotel.be.service;

import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.OrderDetail;
import com.manahotel.be.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class OrderDetailServiceTest {

    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private GoodsRepository goodsRepository;
    @Mock
    private GoodsUnitRepository goodsUnitRepository;
    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OrderRepository orderRepository;
    private OrderDetailService underTest;


    @BeforeEach
    void setUp() {
        underTest = new OrderDetailService(orderDetailRepository, goodsRepository, goodsUnitRepository, invoiceRepository, orderRepository);
    }

    @Test
    public void testDeleteOrderDetails() throws Exception {
        String orderId = "testOrderId";
        String goodsId = "testGoodsId";

        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setInventory(10L);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setGoods(goods);
        orderDetail1.setQuantity(2L);

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setGoods(goods);
        orderDetail2.setQuantity(3L);

        Mockito.when(orderDetailRepository.findOrderDetailByOrder_OrderId(orderId))
                .thenReturn(Arrays.asList(orderDetail1, orderDetail2));

        Mockito.when(goodsRepository.findById(goodsId))
                .thenReturn(Optional.of(goods));

        underTest.deleteOrderDetails(orderId);

        Mockito.verify(orderDetailRepository, Mockito.times(1)).deleteAll(Arrays.asList(orderDetail1, orderDetail2));
        assertEquals(10, goods.getInventory());
    }

    @Test
    public void testDeleteOrderDetailsException() {
        String orderId = "testOrderId";
        String goodsId = "testGoodsId";
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        goods.setInventory(10L);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setQuantity(2L);
        orderDetail1.setGoods(goods);

        Mockito.when(orderDetailRepository.findOrderDetailByOrder_OrderId(orderId))
                .thenReturn(Arrays.asList(orderDetail1));
        Mockito.when(goodsRepository.findById(goodsId))
                .thenReturn(Optional.empty());
        try {
            underTest.deleteOrderDetails(orderId);
        } catch (IllegalStateException e) {
            // Expected exception, do nothing
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getClass().getName());
        }
    }


}