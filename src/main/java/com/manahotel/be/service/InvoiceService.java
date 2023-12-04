package com.manahotel.be.service;

import com.manahotel.be.common.constant.Const;
import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.*;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceReservationDetailRepository invoiceReservationDetailRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OverviewService overviewService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FundBookService fundBookService;

    public ResponseDTO createReservationInvoice(List<ReservationDetailDTO> reservationDetailDTO, InvoiceDTO invoiceDTO, boolean partial){
        log.info("----- Create Reservation Invoice Start -----");
        try{
            Reservation reservation = findReservation(reservationDetailDTO.get(0).getReservationId());
            Customer customer = findCustomer(reservation.getCustomer().getCustomerId());

            Invoice invoice = new Invoice();
            commonMapping(invoiceDTO, invoice);

            invoice.setReservation(reservation);
            invoice.setCustomer(customer);
            invoice.setTotal(invoiceDTO.getTotal());

            invoiceRepository.save(invoice);
            if(partial){
                for (ReservationDetailDTO dto: reservationDetailDTO) {
                    ReservationDetail reservationDetail = findReservationDetail(dto.getReservationDetailId());
                    InvoiceReservationDetail invoiceReservationDetail = new InvoiceReservationDetail();
                    invoiceReservationDetail.setReservationDetail(reservationDetail);
                    invoiceReservationDetail.setInvoice(invoice);
                    invoiceReservationDetailRepository.save(invoiceReservationDetail);
                }
                overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(), "tạo hóa đơn", invoice.getTotal(), new Timestamp(System.currentTimeMillis()));
                fundBookService.writeFundBook(invoice);
                log.info("----- Create Reservation Invoice End -----");
                return ResponseUtils.success("Tạo hóa đơn đặt phòng một phần thành công");
            }else {
                overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(), "tạo hóa đơn", invoice.getTotal(), new Timestamp(System.currentTimeMillis()));
                fundBookService.writeFundBook(invoice);
                log.info("----- Create Reservation Invoice Partial End -----");
                return ResponseUtils.success("Tạo hóa đơn thành công");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("Tạo hóa đơn thất bại");
        }

    }

    public ResponseDTO createPurchaseInvoice(InvoiceDTO invoiceDTO, List<OrderDetailDTO> orderDetailDTOList){
        log.info("----- Create Purchase Invoice Start -----");
        try{
            Reservation reservation = findReservation(Const.RESERVATION_ID);
            Customer customer = findCustomer(Const.CUSTOMER_ID);

            Invoice invoice = new Invoice();
            commonMapping(invoiceDTO, invoice);

            invoice.setReservation(reservation);
            invoice.setCustomer(customer);
            invoice.setTotal(orderService.totalPay(orderDetailDTOList));

            invoiceRepository.save(invoice);

            for (OrderDetailDTO orderDetail : orderDetailDTOList) {
                orderDetailService.createOrderDetail(orderDetail, invoice.getInvoiceId(), Const.ORDER_ID);
            }
            overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(), "tạo hóa đơn", invoice.getTotal(), new Timestamp(System.currentTimeMillis()));
            fundBookService.writeFundBook(invoice);
            log.info("----- Create Purchase Invoice End -----");
            return ResponseUtils.error("Tạo hóa đơn thành công");
        }catch (Exception e){
            log.error("create Purchase Invoice IsFail");
            return ResponseUtils.error("Tạo hóa đơn thất bại");
        }
    }

    private void commonMapping(InvoiceDTO invoiceDTO, Invoice invoice) {
        Invoice latestInvoice = invoiceRepository.findTopByOrderByInvoiceIdDesc();
        String latestId = (latestInvoice == null) ? null : latestInvoice.getInvoiceId();
        String nextId = IdGenerator.generateId(latestId, "HD");
        invoice.setInvoiceId(nextId);

        invoice.setCreatedById(UserUtils.getUser().getStaffId());
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invoice.setStatus(Status.COMPLETE);
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        invoice.setDiscount(invoiceDTO.getDiscount() != null ? invoiceDTO.getDiscount() : invoice.getDiscount());
        invoice.setNote(invoiceDTO.getNote() != null ? invoiceDTO.getNote() : invoice.getNote());
        invoice.setPaidMethod(invoiceDTO.getPaidMethod() != null ? invoiceDTO.getPaidMethod() : invoice.getPaidMethod());
        invoice.setTransactionCode(invoiceDTO.getTransactionCode() != null ? invoiceDTO.getTransactionCode() : invoice.getTransactionCode());
    }

    public ResponseDTO getAllInvoices() {
        return ResponseUtils.success(invoiceRepository.findAll(), "Hiển thị danh sách hóa đơn thành công");
    }

    public ResponseDTO getInvoiceById(String id) {
        return ResponseUtils.success(findInvoice(id), "Hiển thị chi tiết hóa đơn thành công");
    }

    private Invoice findInvoice(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + id));
    }

    private Customer findCustomer(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    private Reservation findReservation(String id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }


    private ReservationDetail findReservationDetail(Long id) {
        return reservationDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail not found with id " + id));
    }
}
