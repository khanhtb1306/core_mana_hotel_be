package com.manahotel.be.service;

import com.manahotel.be.common.constant.Const;
import com.manahotel.be.common.constant.PolicyCont;
import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.InvoiceDTO;
import com.manahotel.be.model.dto.response.OrderDetailDTO;
import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
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

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyDetailRepository policyDetailRepository;

    @Autowired
    private ControlPolicyRepository controlPolicyRepository;

    public ResponseDTO createReservationInvoice(List<ReservationDetailDTO> reservationDetailDTO, InvoiceDTO invoiceDTO){
        log.info("----- Create Reservation Invoice Start -----");
        try{
            Reservation reservation = findReservation(reservationDetailDTO.get(0).getReservationId());
            Customer customer = findCustomer(reservation.getCustomer().getCustomerId());

            if(invoiceDTO.getUsePoint() != null && invoiceDTO.getUsePoint() > 0 && customer.getPoint() > 0){
                customer.setPoint(customer.getPoint() - invoiceDTO.getUsePoint());
            }
            if(invoiceDTO.getTotalReservationLate() != null) {
                List<PolicyDetail> policyDetail = policyDetailRepository.getPolicyDetailByPolicyIdNotStatus6(
                        policyRepository.getPolicyByPolicyName(PolicyCont.SETUP_POINT_SYSTEM).getPolicyId());
                if (policyDetail.get(0).getLimitValue() <= invoiceDTO.getTotalReservationLate()) {
                    customer.setPoint(customer.getPoint() + (int) (invoiceDTO.getTotalReservationLate() / policyDetail.get(0).getLimitValue()));
                }
            }
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
            writeLogFundBookAndRecentActivity(invoiceDTO, invoice);
            log.info("----- Create Reservation Invoice End -----");
                return ResponseUtils.success(invoice.getInvoiceId(),"Tạo hóa đơn thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("Tạo hóa đơn thất bại");
        }
    }

    public ResponseDTO createPurchaseInvoice(InvoiceDTO invoiceDTO, List<OrderDetailDTO> orderDetailDTOList){
        log.info("----- Create Purchase Invoice Start -----");
        try{
            Customer customer = findCustomer(invoiceDTO.getCustomerId() != null ? invoiceDTO.getCustomerId() : Const.CUSTOMER_ID);

            Invoice invoice = new Invoice();
            invoice.setCustomer(customer);
            commonMapping(invoiceDTO, invoice);

            invoice.setTotal(orderService.totalPay(orderDetailDTOList));

            invoiceRepository.save(invoice);

            orderDetailDTOList.forEach(orderDetail -> {
                orderDetailService.createOrderDetail(orderDetail, invoice.getInvoiceId(), Const.ORDER_ID);
            });
            writeLogFundBookAndRecentActivity(invoiceDTO, invoice);
            log.info("----- Create Purchase Invoice End -----");
            return ResponseUtils.error("Tạo hóa đơn thành công");
        }catch (Exception e){
            log.error("create Purchase Invoice IsFail");
            return ResponseUtils.error("Tạo hóa đơn thất bại");
        }
    }

    private void writeLogFundBookAndRecentActivity(InvoiceDTO invoiceDTO, Invoice invoice) {
        Float total = invoice.getTotal() - invoice.getDiscount() + invoice.getPriceOther();
        Float deposit = invoiceDTO.getPrePail() != null ? invoiceDTO.getPrePail() : 0;
        fundBookService.writeFundBook(
                invoice.getInvoiceId(),
                invoiceDTO.getPaidMethod(),
                total >= deposit ? total - deposit: - total,
                invoiceDTO.getTransactionCode() != null ? invoiceDTO.getTransactionCode() : "");
        overviewService.writeRecentActivity(UserUtils.getUser().getStaffName(),
                "tạo hóa đơn",
                invoice.getTotal() + invoice.getPriceOther() - invoice.getDiscount(),
                new Timestamp(System.currentTimeMillis()));
    }

    private void commonMapping(InvoiceDTO invoiceDTO, Invoice invoice) {
        Invoice latestInvoice = invoiceRepository.findTopByOrderByInvoiceIdDesc();
        String latestId = (latestInvoice == null) ? null : latestInvoice.getInvoiceId();
        String nextId = IdGenerator.generateId(latestId, "HD");
        invoice.setInvoiceId(nextId);

        invoice.setStaff(UserUtils.getUser());
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invoice.setStatus(Status.COMPLETE);
        invoice.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        invoice.setDiscount(invoiceDTO.getDiscount() != null ? invoiceDTO.getDiscount() : 0);
        invoice.setNote(invoiceDTO.getNote() != null ? invoiceDTO.getNote() : invoice.getNote());
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

                //lấy Control policy
                List<ControlPolicy> controlPolicy = controlPolicyRepository.findControlPolicyByReservationDetail_ReservationDetailId(ird.getReservationDetail().getReservationDetailId());

                reservationDetailWithOrder.put("reservationDetail", reservationDetail);
                reservationDetailWithOrder.put("ListControlPolicy", controlPolicy);
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
                    .collect(Collectors.toList());

            // Retrieve details for all invoices at once
            List<Object> invoiceDetails = invoices.stream()
                    .map(invoice -> getInvoiceById(invoice.getInvoiceId()).getResult())
                    .collect(Collectors.toList());

            log.info("----- Get Invoice By Reservation End -----");
            return ResponseUtils.success(invoiceDetails, "Lấy hóa đơn theo " + reservation_Id + " thành công");
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
