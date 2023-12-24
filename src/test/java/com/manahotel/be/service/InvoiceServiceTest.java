package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.response.InvoiceDTO;
import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Invoice;
import com.manahotel.be.model.entity.InvoiceReservationDetail;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.repository.InvoiceRepository;
import com.manahotel.be.repository.InvoiceReservationDetailRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private InvoiceReservationDetailRepository invoiceReservationDetailRepository;

    @Mock
    private ReservationDetailRepository reservationDetailRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private FundBookService fundBookService;

    @Mock
    private OverviewService overviewService;

    @Mock
    private OrderDetailService orderDetailService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void createReservationInvoice() {
        // Mock data
        ReservationDetailDTO reservationDetailDTO = new ReservationDetailDTO();
        reservationDetailDTO.setReservationDetailId(1L);
        List<ReservationDetailDTO> reservationDetailDTOList = Collections.singletonList(reservationDetailDTO);

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setUsePoint(10L);
        invoiceDTO.setTotalReservationLate(200F);

        // Mock repository behavior
        when(reservationDetailRepository.findById(1L)).thenReturn(java.util.Optional.of(new ReservationDetail()));
        when(invoiceRepository.findTopByOrderByInvoiceIdDesc()).thenReturn(new Invoice());
        when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(new Invoice());
        when(invoiceReservationDetailRepository.save(Mockito.any(InvoiceReservationDetail.class))).thenReturn(new InvoiceReservationDetail());
        when(reservationDetailRepository.save(Mockito.any(ReservationDetail.class))).thenReturn(new ReservationDetail());

        // Call the service method
        ResponseDTO responseDTO = invoiceService.createReservationInvoice(reservationDetailDTOList, invoiceDTO);

        // Verify the results
        assertEquals("Tạo hóa đơn thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void createPurchaseInvoice() {
        // Mock data
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setCustomerId("customer123");

        // Mock repository behavior
        when(invoiceRepository.findTopByOrderByInvoiceIdDesc()).thenReturn(new Invoice());
        when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(new Invoice());
        when(orderService.totalPay(Mockito.anyList())).thenReturn(100F);

        // Call the service method
        ResponseDTO responseDTO = invoiceService.createPurchaseInvoice(invoiceDTO, Collections.emptyList());

        // Verify the results
        assertEquals("Tạo hóa đơn thất bại", responseDTO.getDisplayMessage());
    }
}