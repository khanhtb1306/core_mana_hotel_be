package com.manahotel.be.service;

import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ReservationDetailRepository reservationDetailRepository;

    @Mock
    private StaffRepository staffRepository;

    private OrderService underTest;


//    @BeforeEach
//    void setUp() {
//        underTest = new OrderService(orderDetailRepository, orderRepository, reservationDetailRepository, staffRepository);
//    }
//
//    @Test
//    public void testCreateOrderSuccess() {
//        // Arrange
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setReservationDetailId(1L);
//
//        Order latestOrder = new Order();
//        latestOrder.setOrderId("Or001");
//
//        String staffName = "staffName";
//        Staff staff = new Staff();
//        staff.setUsername(staffName);
//        staff.setStaffId(1L);
//
//        ReservationDetail reservationDetail = new ReservationDetail();
//
//        Mockito.when(orderRepository.findTopByOrderByOrderIdDesc()).thenReturn(latestOrder);
//
//        Mockito.when(authentication.getName()).thenReturn(staffName);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        Mockito.when(staffRepository.findByUsername(staffName)).thenReturn(staff);
//
//        Mockito.when(reservationDetailRepository.findById(orderDTO.getReservationDetailId())).thenReturn(Optional.of(reservationDetail));
//        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        // Act
//        ResponseDTO response = underTest.createOrder(orderDTO);
//
//        // Assert
//        Mockito.verify(orderRepository).findTopByOrderByOrderIdDesc();
//        Mockito.verify(orderRepository).save(Mockito.any(Order.class));
//        assertEquals("Thêm hóa đơn thành công", response.getDisplayMessage());
//    }
//
//    @Test
//    public void testCreateOrderException() {
//        // Arrange
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setReservationDetailId(1L);
//        Order latestOrder = new Order();
//        latestOrder.setOrderId("Or001");
//        Mockito.when(orderRepository.findTopByOrderByOrderIdDesc()).thenReturn(latestOrder);
//        Mockito.when(reservationDetailRepository.findById(orderDTO.getReservationDetailId())).thenThrow(new IllegalStateException("reservation with id not exists"));
//
//
//        // Act and Assert
//        ResponseDTO response = underTest.createOrder(orderDTO);
//
//        // Assert
//        assertEquals("Thêm hóa đơn thất bại", response.getDisplayMessage());
//    }
//    @Test
//    public void testCreateOrderReservationDetailNotFoundException() {
//        // Arrange
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setReservationDetailId(1L);
//
//        Mockito.when(orderRepository.findTopByOrderByOrderIdDesc()).thenThrow(new RuntimeException());
//
//        // Act and Assert
//        ResponseDTO response = underTest.createOrder(orderDTO);
//
//        // Assert
//        assertEquals("Thêm hóa đơn thất bại", response.getDisplayMessage());
//    }

    @Test
    public void testFindStaffSuccess() {
        // Arrange
        String staffName = "staffName";
        Staff staff = new Staff();
        staff.setUsername(staffName);

        Mockito.when(authentication.getName()).thenReturn(staffName);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(staffRepository.findByUsername(staffName)).thenReturn(staff);

        // Act
        Staff result = underTest.findStaff();

        // Assert
        assertEquals(staff, result);
    }

//    @Test
//    public void testTotalPay() {
//        String orderId = "testOrderId";
//        OrderDetail orderDetail1 = new OrderDetail();
//        orderDetail1.setPrice(100.0f);
//        orderDetail1.setQuantity(2L);
//
//        OrderDetail orderDetail2 = new OrderDetail();
//        orderDetail2.setPrice(200.0f);
//        orderDetail2.setQuantity(3L);
//
//        Mockito.when(orderDetailRepository.findByOrder_OrderId(orderId))
//                .thenReturn(Arrays.asList(orderDetail1, orderDetail2));
//
//        Float expectedTotal = 800.0f ;
//        Float actualTotal = underTest.totalPay(orderId);
//
//        assertEquals(expectedTotal, actualTotal);
//    }

//    @Test
//    public void testUpdateTotalPayOrder() {
//        Long reservationDetailId = 1L;
//        String orderId = "testOrderId";
//        Order order = new Order();
//        order.setOrderId(orderId);
//
//        Mockito.when(orderRepository.findByReservationDetail_ReservationDetailId(reservationDetailId))
//                .thenReturn(order);
//
//        ResponseDTO response = underTest.updateTotalPayOrder(reservationDetailId);
//
//        assertEquals("Cập nhật hóa đơn thành công", response.getDisplayMessage());
//    }
//    @Test
//    public void testUpdateTotalPayOrderException() {
//        Long reservationDetailId = 1L;
//
//        Mockito.when(orderRepository.findByReservationDetail_ReservationDetailId(reservationDetailId))
//                .thenReturn(null);
//
//        ResponseDTO response = underTest.updateTotalPayOrder(reservationDetailId);
//
//        assertEquals("Cập nhật hóa đơn thất bại", response.getDisplayMessage());
//
//    }



}