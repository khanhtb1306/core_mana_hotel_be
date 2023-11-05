//package com.manahotel.be.service;
//
//import com.manahotel.be.model.dto.OrderDetailDTO;
//import com.manahotel.be.model.dto.ResponseDTO;
//import com.manahotel.be.model.entity.Customer;
//import com.manahotel.be.model.entity.Goods;
//import com.manahotel.be.model.entity.Order;
//import com.manahotel.be.model.entity.OrderDetail;
//import com.manahotel.be.repository.CustomerRepository;
//import com.manahotel.be.repository.GoodsRepository;
//import com.manahotel.be.repository.OrderDetailRepository;
//import com.manahotel.be.repository.OrderRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//@DataJpaTest
//@ExtendWith(MockitoExtension.class)
//class OrderDetailServiceTest {
//
//    @Mock
//    private OrderDetailRepository orderDetailRepository;
//    @Mock
//    private GoodsRepository goodsRepository;
//
//    @Mock
//    private OrderRepository orderRepository;
//
//    private OrderDetailService underTest;
//
//    @Captor
//    private ArgumentCaptor<Customer> customerArgumentCaptor;
//
//    @BeforeEach
//    void setUp() {
//        underTest = new OrderDetailService(orderDetailRepository,goodsRepository,orderRepository);
//    }
//
//    @Test
//    public void testCreateOrderDetailSuccess() {
//        // Arrange
//        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//        orderDetailDTO.setGoodsId("goodsId");
//        orderDetailDTO.setOrderId("orderId");
//
//        Goods goods = new Goods();
//        Order order = new Order();
//
//        Mockito.when(goodsRepository.findById(orderDetailDTO.getGoodsId())).thenReturn(Optional.of(goods));
//        Mockito.when(orderRepository.findById(orderDetailDTO.getOrderId())).thenReturn(Optional.of(order));
//
//        // Act
//        ResponseDTO response = underTest.createOrderDetail(orderDetailDTO);
//
//        // Assert
//        Mockito.verify(goodsRepository).findById(orderDetailDTO.getGoodsId());
//        Mockito.verify(orderRepository).findById(orderDetailDTO.getOrderId());
//        Mockito.verify(orderDetailRepository).save(Mockito.any(OrderDetail.class));
//        assertEquals("Thêm chi tiết hóa đơn thành công", response.getDisplayMessage());
//    }
//
//    @Test
//    public void testCreateOrderDetailCatchException() {
//        // Arrange
//        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//        orderDetailDTO.setGoodsId("goodsId");
//        orderDetailDTO.setOrderId("orderId");
//
//        Goods goods = new Goods();
//        Order order = new Order();
//
//        Mockito.when(goodsRepository.findById(orderDetailDTO.getGoodsId())).thenReturn(Optional.of(goods));
//        Mockito.when(orderRepository.findById(orderDetailDTO.getOrderId())).thenReturn(Optional.of(order));
//        Mockito.when(orderDetailRepository.save(Mockito.any(OrderDetail.class))).thenThrow(new RuntimeException());
//
//        // Act and Assert
//        ResponseDTO response = underTest.createOrderDetail(orderDetailDTO);
//
//        // Assert
//        assertEquals("Thêm chi tiết hóa đơn Thất bại", response.getDisplayMessage());
//    }
//
//
//    @Test
//    public void testCreateOrderDetailGoodsNotFoundException() {
//        // Arrange
//        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//        orderDetailDTO.setGoodsId("goodsId");
//        orderDetailDTO.setOrderId("orderId");
//
//        Mockito.when(goodsRepository.findById(orderDetailDTO.getGoodsId())).thenThrow(new IllegalStateException("Not found goods"));
//
//        // Act and Assert
//        ResponseDTO response = underTest.createOrderDetail(orderDetailDTO);
//
//        // Assert
//        assertEquals("Thêm chi tiết hóa đơn Thất bại", response.getDisplayMessage());
//    }
//
//    @Test
//    public void testCreateOrderDetailOrderNotFoundException() {
//        // Arrange
//        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
//        orderDetailDTO.setGoodsId("goodsId");
//        orderDetailDTO.setOrderId("orderId");
//        Goods goods = new Goods();
//
//        Mockito.when(goodsRepository.findById(orderDetailDTO.getGoodsId())).thenReturn(Optional.of(goods));
//        Mockito.when(orderRepository.findById(orderDetailDTO.getOrderId())).thenThrow(new IllegalStateException("Not found orders"));
//
//        // Act and Assert
//        ResponseDTO response = underTest.createOrderDetail(orderDetailDTO);
//
//        // Assert
//        assertEquals("Thêm chi tiết hóa đơn Thất bại", response.getDisplayMessage());
//    }
//
//
//
//}