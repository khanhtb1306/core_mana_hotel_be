package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.response.ReservationDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.Staff;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.PriceListRepository;
import com.manahotel.be.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PriceListRepository priceListRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        // Reset the mocks before each test
        Mockito.reset(reservationRepository);
    }

    @Test
    void createReservation() {
        Staff mockStaff = new Staff();
        mockStaff.setStaffId(123L);  // Set the staff ID as needed
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockStaff);

        // Set up the SecurityContext to use the mock Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Set the SecurityContext to use the mocked SecurityContext
        SecurityContextHolder.setContext(securityContext);

        // Mock data
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerId("1");
        reservationDTO.setPriceListId("2");
        reservationDTO.setStatus(Status.CHECK_IN);

        // Mock repository behavior
        // Mock repository behavior
        when(reservationRepository.findTopByOrderByReservationIdDesc()).thenReturn(null);
        when(customerRepository.findById("1")).thenReturn(java.util.Optional.of(new Customer())); // Mock the customer
        when(priceListRepository.findById("2")).thenReturn(java.util.Optional.of(new PriceList())); // Mock the price list
        when(reservationRepository.save(any(Reservation.class))).thenReturn(new Reservation());

        // Call the service method
        ResponseDTO responseDTO = reservationService.createReservation(reservationDTO);

        // Verify the results
        assertEquals("Tạo đơn đặt phòng thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void updateReservation() {

        Staff mockStaff = new Staff();
        mockStaff.setStaffId(123L);  // Set the staff ID as needed
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(mockStaff);

        // Set up the SecurityContext to use the mock Authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Set the SecurityContext to use the mocked SecurityContext
        SecurityContextHolder.setContext(securityContext);

        // Mock data
        String reservationId = "1";
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCustomerId("2");
        reservationDTO.setPriceListId("3");
        reservationDTO.setStatus(Status.CHECK_IN);

        Reservation existingReservation = new Reservation();
        existingReservation.setReservationId(reservationId);

        // Mock repository behavior
        when(reservationRepository.findById(reservationId)).thenReturn(java.util.Optional.of(existingReservation));
        when(customerRepository.findById("2")).thenReturn(java.util.Optional.of(new Customer())); // Mock the customer
        when(priceListRepository.findById("3")).thenReturn(java.util.Optional.of(new PriceList())); // Mock the price list
        when(reservationRepository.save(any(Reservation.class))).thenReturn(existingReservation);

        // Call the service method
        ResponseDTO responseDTO = reservationService.updateReservation(reservationId, reservationDTO);

        // Verify the results
        assertEquals("Cập nhật đơn đặt phòng thành công", responseDTO.getDisplayMessage());
    }
}