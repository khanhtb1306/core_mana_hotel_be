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
import java.util.*;
import java.util.stream.Collectors;

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

    public ResponseDTO createReservationInvoice(List<ReservationDetailDTO> reservationDetailDTO, InvoiceDTO invoiceDTO){
        log.info("----- Create Reservation Invoice Start -----");
        try{
            Reservation reservation = findReservation(reservationDetailDTO.get(0).getReservationId());
            Customer customer = findCustomer(reservation.getCustomer().getCustomerId());

            Invoice invoice = new Invoice();
            commonMapping(invoiceDTO, invoice);

            invoice.setCustomer(customer);
            invoice.setTotal(invoiceDTO.getTotal());

            invoiceRepository.save(invoice);
                reservationDetailDTO.forEach(dto -> {
                    ReservationDetail reservationDetail = findReservationDetail(dto.getReservationDetailId());
                    InvoiceReservationDetail invoiceReservationDetail = new InvoiceReservationDetail();
                    invoiceReservationDetail.setReservationDetail(reservationDetail);
                    invoiceReservationDetail.setInvoice(invoice);
                    invoiceReservationDetailRepository.save(invoiceReservationDetail);
                    //Update Status
                    reservationDetail.setStatus(Status.DONE);
                    reservationDetailRepository.save(reservationDetail);
                    overviewService.writeTopRoomClass(reservationDetail.getRoom().getRoomId(), reservationDetail);
                });
                if(invoice.getPaidMethod().equals(Status.CASH)){
                    overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(), "tạo hóa đơn", invoice.getTotal() + invoice.getPriceOther() - invoice.getDiscount(), new Timestamp(System.currentTimeMillis()));
                    fundBookService.writeFundBook(invoice.getInvoiceId(), invoice.getPaidMethod(), (invoice.getTotal() - invoice.getDiscount() + invoice.getPriceOther()), invoice.getTransactionCode());
                }
                log.info("----- Create Reservation Invoice End -----");
                return ResponseUtils.success(invoice.getInvoiceId(),"Tạo hóa đơn thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("Tạo hóa đơn thất bại");
        }
    }

    public ResponseDTO updateReservationInvoice(InvoiceDTO invoiceDTO){
        log.info("----- Update Reservation Invoice Start -----");
        try{
            Invoice invoice =  findInvoice(invoiceDTO.getInvoiceId());
            invoice.setTransactionCode(invoiceDTO.getTransactionCode() != null ? invoiceDTO.getTransactionCode() : invoice.getTransactionCode());
            invoice.setPaidMethod(invoiceDTO.getPaidMethod() != null ? invoiceDTO.getPaidMethod() : invoice.getPaidMethod());
            invoice.setStatus(invoiceDTO.getStatus() != null ? invoiceDTO.getStatus() : invoice.getStatus());
            invoiceRepository.save(invoice);
            if(invoice.getPaidMethod().equals(Status.TRANSFER)) {
                overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(), "tạo hóa đơn", invoice.getTotal() + invoice.getPriceOther() - invoice.getDiscount(), new Timestamp(System.currentTimeMillis()));
                fundBookService.writeFundBook(invoice.getInvoiceId(), invoice.getPaidMethod(), (invoice.getTotal() - invoice.getDiscount() + invoice.getPriceOther()), invoice.getTransactionCode());
            }
            log.info("----- Update Reservation Invoice End -----");
            return ResponseUtils.success(invoice.getInvoiceId(),"Cập nhật hóa đơn thành công");
        }catch (Exception e){
            log.error("updateReservationInvoice_isFail");
            return ResponseUtils.success("Cập nhật hóa đơn thất bại");
        }
    }

    public ResponseDTO createPurchaseInvoice(InvoiceDTO invoiceDTO, List<OrderDetailDTO> orderDetailDTOList){
        log.info("----- Create Purchase Invoice Start -----");
        try{
            Customer customer = findCustomer(Const.CUSTOMER_ID);

            Invoice invoice = new Invoice();
            commonMapping(invoiceDTO, invoice);

            invoice.setCustomer(customer);
            invoice.setTotal(orderService.totalPay(orderDetailDTOList));

            invoiceRepository.save(invoice);

            orderDetailDTOList.forEach(orderDetail -> {
                orderDetailService.createOrderDetail(orderDetail, invoice.getInvoiceId(), Const.ORDER_ID);
            });
            if(invoice.getPaidMethod().equals(Status.CASH)){
                overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(), "tạo hóa đơn", invoice.getTotal(), new Timestamp(System.currentTimeMillis()));
                fundBookService.writeFundBook(invoice.getInvoiceId(), invoice.getPaidMethod(), (invoice.getTotal() - invoice.getDiscount() + invoice.getPriceOther()), invoice.getTransactionCode());
            }
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

        invoice.setStaff(UserUtils.getUser());
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invoice.setPaidMethod(invoiceDTO.getPaidMethod() != null ? invoiceDTO.getPaidMethod() : invoice.getPaidMethod());
        invoice.setStatus(invoice.getPaidMethod().equals(Status.CASH) ? Status.COMPLETE : Status.UNCONFIRMED);
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invoice.setDiscount(invoiceDTO.getDiscount() != null ? invoiceDTO.getDiscount() : 0);
        invoice.setNote(invoiceDTO.getNote() != null ? invoiceDTO.getNote() : invoice.getNote());
        invoice.setTransactionCode(invoiceDTO.getTransactionCode() != null ? invoiceDTO.getTransactionCode() : invoice.getTransactionCode());
        invoice.setPriceOther(invoiceDTO.getPriceOther() != null ? invoiceDTO.getPriceOther() : 0);
    }

    public ResponseDTO getAllInvoices() {
        List<Invoice> invoice = invoiceRepository.findAllByOrderByInvoiceIdDesc();
        return ResponseUtils.success(invoice, "Hiển thị danh sách hóa đơn thành công");
    }

    public ResponseDTO getInvoiceById(String id) {
        Map<String, Object> invoiceInfo = new HashMap<>();
        try{
            Invoice invoice = findInvoice(id);
            List<Object> reservationDetails = new ArrayList<>();
            List<InvoiceReservationDetail> invoiceReservationDetails = invoiceReservationDetailRepository
                    .findInvoiceReservationDetailByInvoice_InvoiceId(invoice.getInvoiceId());
            for(InvoiceReservationDetail ird: invoiceReservationDetails){
                Map<String, Object> reservationDetailWithOrder = new HashMap<>();
                //lấy reservationDetail
                ReservationDetail reservationDetail = findReservationDetail(ird.getReservationDetail().getReservationDetailId());
                //lấy order
                Object order = orderService.getOrderByReservationDetailId(reservationDetail.getReservationDetailId()).getResult();

                reservationDetailWithOrder.put("reservationDetail", reservationDetail);
                reservationDetailWithOrder.put("ListOrder", order);

                reservationDetails.add(reservationDetailWithOrder);
            }
            invoiceInfo.put("Invoice", invoice);
            invoiceInfo.put("ListReservationOfInvoice", reservationDetails);
        }catch (Exception e){
            log.error("getInvoiceById_isFail" + e.getMessage());
        }
        return ResponseUtils.success(invoiceInfo, "Hiển thị chi tiết hóa đơn thành công");
    }

    public ResponseDTO getInvoiceByReservation(String reservation_Id) {
        log.info("----- Get Invoice By Reservation Start -----");
        try {
            List<Invoice> invoices = reservationDetailRepository
                    .findReservationDetailByReservation_ReservationId(reservation_Id)
                    .stream()
                    .map(reservationDetail -> {
                        InvoiceReservationDetail invoiceReservationDetails = invoiceReservationDetailRepository
                                .findInvoiceReservationDetailByReservationDetail_ReservationDetailId(reservationDetail.getReservationDetailId());
                        return (invoiceReservationDetails != null) ? invoiceReservationDetails.getInvoice() : null;
                    })
                    .filter(Objects::nonNull)
                    .distinct()
                    .map(invoice -> findInvoice(invoice.getInvoiceId()))
                    .collect(Collectors.toList());
            log.info("----- Get Invoice By Reservation End -----");
            return ResponseUtils.success(invoices, "Lấy hóa đơn theo " + reservation_Id + " thành công");
        } catch (Exception e) {
            log.error("getInvoiceByReservation_isFail: " + e.getMessage());
            return ResponseUtils.error("Lấy hóa đơn theo " + reservation_Id + " Thất bại");
        }
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
