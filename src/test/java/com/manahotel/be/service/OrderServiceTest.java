package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.OrderDTO;
import com.manahotel.be.model.dto.response.OrderDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private OrderRepository orderRepository;

    @Mock
    private ReservationDetailRepository reservationDetailRepository;

    @Mock
    private StaffRepository staffRepository;

    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private FundBookService fundBookService;

    @Mock OverviewService overviewService;

    private OrderService underTest;


    @BeforeEach
    void setUp() {
        underTest = new OrderService(orderRepository, reservationDetailRepository, staffRepository,orderDetailRepository, orderDetailService, fundBookService, overviewService);
    }

    @Test
    public void testCreateOrderReservationDetailNotFoundException() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setReservationDetailId(1L);
        List<OrderDetailDTO> orderDetailDTOList = Arrays.asList();

        Mockito.when(orderRepository.findTopByOrderByOrderIdDesc()).thenThrow(new RuntimeException());

        ResponseDTO response = underTest.createOrder(orderDTO, orderDetailDTOList);

        assertEquals("Thêm hóa đơn thất bại", response.getDisplayMessage());
    }

    @Test
    public void testCreateOrder() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setReservationDetailId(1L);
        Order latestOrder = new Order();
        latestOrder.setOrderId("Or001");

        String staffName = "staffName";
        Staff staff = new Staff();
        staff.setUsername(staffName);
        staff.setStaffId(1L);

        ReservationDetail reservationDetail = new ReservationDetail();

        Mockito.when(orderRepository.findTopByOrderByOrderIdDesc()).thenReturn(latestOrder);

        Mockito.when(authentication.getName()).thenReturn(staffName);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(staffRepository.findByUsername(staffName)).thenReturn(staff);

        Mockito.when(reservationDetailRepository.findById(orderDTO.getReservationDetailId())).thenReturn(Optional.of(reservationDetail));

        OrderDetailDTO orderDetail1 = new OrderDetailDTO();
        orderDetail1.setPrice(100.0f);
        orderDetail1.setQuantity(2L);

        OrderDetailDTO orderDetail2 = new OrderDetailDTO();
        orderDetail2.setPrice(200.0f);
        orderDetail2.setQuantity(3L);

        List<OrderDetailDTO> orderDetailDTOList = Arrays.asList(orderDetail1, orderDetail2);

        ResponseDTO response = underTest.createOrder(orderDTO, orderDetailDTOList);

        assertEquals("Thêm hóa đơn thành công", response.getDisplayMessage());
    }

    @Test
    public void testFindStaffSuccess() {
        String staffName = "staffName";
        Staff staff = new Staff();
        staff.setUsername(staffName);

        Mockito.when(authentication.getName()).thenReturn(staffName);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(staffRepository.findByUsername(staffName)).thenReturn(staff);

        Staff result = underTest.findStaff();

        assertEquals(staff, result);
    }

    @Test
    public void testTotalPay() {
        OrderDetailDTO order1 = new OrderDetailDTO();
        order1.setPrice(100.0f);
        order1.setQuantity(2L);

        OrderDetailDTO order2 = new OrderDetailDTO();
        order2.setPrice(200.0f);
        order2.setQuantity(3L);

        List<OrderDetailDTO> orderDetailDTOList = Arrays.asList(order1, order2);

        Float total = underTest.totalPay(orderDetailDTOList);

        assertEquals(800.0f, total);
    }
    @Test
    public void testDeleteOrder() {
        String orderId = "OR01";
        Order order = new Order();
        order.setOrderId("Or001");
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        ResponseDTO response = underTest.deleteOrder(orderId);
        assertEquals("Xoá hóa đơn thành công", response.getDisplayMessage());
    }
    @Test
    public void testDeleteOrderException() {
        String orderId = "OR01";
        Order order = new Order();
        order.setOrderId("Or001");
        Mockito.when(orderRepository.findById(orderId)).thenThrow(new IllegalStateException("order with id not exists"));
        ResponseDTO response = underTest.deleteOrder(orderId);
        assertEquals("Xóa hóa đơn thất bại", response.getDisplayMessage());
    }
    @Test
    public void testUpdateOrder() throws Exception {
        String orderId = "OR01";
        Order order = new Order();
        order.setOrderId("Or001");
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        OrderDetailDTO orderDetail1 = new OrderDetailDTO();
        orderDetail1.setPrice(100.0f);
        orderDetail1.setQuantity(2L);

        OrderDetailDTO orderDetail2 = new OrderDetailDTO();
        orderDetail2.setPrice(200.0f);
        orderDetail2.setQuantity(3L);

        List<OrderDetailDTO> orderDetailDTOList = Arrays.asList(orderDetail1, orderDetail2);

        ResponseDTO response = underTest.updateOrder(orderId, orderDetailDTOList);

        assertEquals("Cập nhật hóa đơn thành công", response.getDisplayMessage());
    }

}