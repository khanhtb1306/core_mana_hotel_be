package com.manahotel.be.service;

import com.manahotel.be.model.dto.response.ReservationDetailCustomerDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReservationDetailCustomerServiceTest {

    @Mock
    private ReservationDetailCustomerRepository repository;

    @Mock
    private ReservationDetailRepository repository2;

    @Mock
    private CustomerRepository repository3;

    @Mock
    private ReservationRepository repository4;

    @InjectMocks
    private ReservationDetailCustomerService service;

    @Test
    void createRDCustomer() {
        // Mock data
        ReservationDetailCustomerDTO dto = new ReservationDetailCustomerDTO();
        dto.setCustomerId("sampleCustomerId");
        dto.setReservationDetailId(1L);

        // Mock repository behavior
        when(repository.save(Mockito.any())).thenReturn(new ReservationDetailCustomer());
        when(repository2.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(null));

        // Call the service method
        ResponseDTO responseDTO = service.createRDCustomer(dto, true);

        // Verify the results
        assertEquals("Tạo thông tin khách hàng theo phòng thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void updateRDCustomer() {
        // Mock data
        ReservationDetailCustomerDTO dto = new ReservationDetailCustomerDTO();
        dto.setReservationDetailId(1L);

        // Mock repository behavior
        when(repository2.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(null));
        when(repository4.save(Mockito.any())).thenReturn(null);

        // Call the service method
        ResponseDTO responseDTO = service.updateRDCustomer(dto, true);

        // Verify the results
        assertEquals("Tạo thông tin khách hàng theo phòng thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteRDCustomer() {
        // Mock data
        Long id = 1L;

        // Mock repository behavior
        when(repository.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(new ReservationDetailCustomer()));
        when(repository2.findById(Mockito.any())).thenReturn(java.util.Optional.ofNullable(null));
        when(repository4.save(Mockito.any())).thenReturn(null);

        // Call the service method
        ResponseDTO responseDTO = service.deleteRDCustomer(id, true);

        // Verify the results
        assertEquals("Xóa thông tin khách hàng theo phòng thất bại", responseDTO.getDisplayMessage());
    }
}