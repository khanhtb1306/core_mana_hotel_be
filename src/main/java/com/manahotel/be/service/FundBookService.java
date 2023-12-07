package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.FundBookDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.response.FundBookResponse;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class FundBookService {

    @Autowired
    private FundBookRepository repository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private InvoiceReservationDetailRepository invoiceReservationDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    public ResponseDTO getAll(String time, boolean isMonth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(time, formatter);

        return ResponseUtils.success(isMonth ? repository.getAllFundBookByMonth(date.getMonthValue(), date.getYear()) : repository.getAllFundBookByYear(date.getYear()), "Hiển thị danh sách sổ quỹ thành công");
    }

    public ResponseDTO createFundBook(FundBookDTO fundBookDTO) {
        try {
            log.info("----- Start create fund book -----");
            FundBook latestOther = repository.findTopByFundBookIdContainingOrderByFundBookIdDesc("TTK");
            String latestOtherId = (latestOther == null) ? null : latestOther.getFundBookId();
            String nextId = IdGenerator.generateId(latestOtherId, "TTK");

            FundBook fundBook = new FundBook();
            fundBook.setFundBookId(nextId);

            Timestamp time = Optional.ofNullable(fundBookDTO.getTime())
                    .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
            fundBook.setTime(time);

            fundBook.setType(fundBookDTO.getType());
            fundBook.setPaidMethod(Status.CASH);
            fundBook.setValue(fundBookDTO.getValue());
            fundBook.setPayerReceiver(fundBookDTO.getPayerReceiver());
            fundBook.setStaff(fundBookDTO.getStaff());
            fundBook.setNote(fundBookDTO.getNote());
            fundBook.setStatus(Status.COMPLETE);

            repository.save(fundBook);
            log.info("----- End create fund book -----");

            return ResponseUtils.success("Tạo phiếu thành công");
        }
        catch (Exception e) {
            log.info("----- Create fund book failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo phiếu thất bại");
        }
    }

    public void writeFundBook(String fundBookId, String paidMethod, Float value, String transactionCode){
        log.info("----- Write Fund Book Start  -----");
        try{
            FundBook fundBook = findFundBook("TT" + fundBookId);
            if(fundBook != null){
                fundBook.setPaidMethod(paidMethod);
            }else {
                fundBook = new FundBook();
                fundBook.setFundBookId("TT" + fundBookId);
                fundBook.setTime(new Timestamp(System.currentTimeMillis()));
                fundBook.setType(Status.INCOME);
                fundBook.setValue(value);
                fundBook.setPayerReceiver("Khách Hàng");
                fundBook.setStaff(UserUtils.getUser().getStaffName());
                fundBook.setNote("Thu tiền khách trả");
                fundBook.setStatus(Status.COMPLETE);
                fundBook.setTransactionCode(transactionCode);
            }
            repository.save(fundBook);
            log.info("writeFundBook_isSuccess");
        }catch (Exception e){
            log.error("writeFundBook_isFail");
        }
        log.info("----- Write Fund Book Start  -----");
    }


    public ResponseDTO updateFundBook(String id, FundBookDTO fundBookDTO) {
        try {
            log.info("----- Start update fund book -----");
            FundBook fundBook = findFundBook(id);

            Timestamp time = Optional.ofNullable(fundBookDTO.getTime() != null ? fundBookDTO.getTime() : fundBook.getTime())
                    .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
            fundBook.setTime(time);

            fundBook.setStaff(fundBookDTO.getStaff() != null ? fundBookDTO.getStaff() : fundBook.getStaff());
            fundBook.setPaidMethod(fundBookDTO.getPaidMethod() != null ? fundBookDTO.getPaidMethod() : fundBook.getPaidMethod());
            fundBook.setNote(fundBookDTO.getNote() != null ? fundBookDTO.getNote() : fundBook.getNote());
            fundBook.setStatus(fundBookDTO.getStatus() != null ? fundBookDTO.getStatus() : fundBook.getStatus());

            repository.save(fundBook);
            log.info("----- End create fund book -----");

            return ResponseUtils.success("Cập nhật phiếu thành công");
        }
        catch (Exception e) {
            log.info("----- Create fund book failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật phiếu thất bại");
        }
    }

    public ResponseDTO getFundBookByReservation(String reservationId) {
        log.info("----- Get Fund Book By Reservation Start -----");
        try {
            List<FundBook> combinedFundBookList = new ArrayList<>();
            List<FundBook> fundBookList = repository.findByFundBookIdContaining(reservationId);
            combinedFundBookList.addAll(fundBookList);
            List<ReservationDetail> reservationDetailList = reservationDetailRepository.findReservationDetailByReservation_ReservationId(reservationId);
            for (ReservationDetail rd : reservationDetailList) {
                InvoiceReservationDetail invoiceReservationDetailList = invoiceReservationDetailRepository.findInvoiceReservationDetailByReservationDetail_ReservationDetailId(rd.getReservationDetailId());
                List<FundBook> fundBookList2 = repository.findByFundBookIdContaining(invoiceReservationDetailList.getInvoice().getInvoiceId());
                combinedFundBookList.addAll(fundBookList2);
                List<Order> orderList = orderRepository.findOrderByReservationDetailAndStatus(rd, Status.PAID);
                for (Order order : orderList) {
                    List<FundBook> fundBookList3 = repository.findByFundBookIdContaining(order.getOrderId());
                    combinedFundBookList.addAll(fundBookList3);
                }
            }
            Collections.sort(combinedFundBookList, Comparator.comparing(FundBook::getTime));
            log.error("getFundBookByReservation_Successfully");
            log.info("----- Get Fund Book By Reservation End -----");
            return ResponseUtils.success(combinedFundBookList, "getFundBookByReservation_successfully");
        }catch (Exception e){
            log.error("getFundBookByReservation_isFail");
            return ResponseUtils.success("getFundBookByReservation_isFail");
        }
    }

    public ResponseDTO getFundBookSummary(String time, boolean isMonth) {
        try {
            log.info("----- Start get summary -----");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(time, formatter);

            Float previousIncome = isMonth ? repository.getAllIncomeByMonth(date.getMonthValue() - 1, date.getYear()) : repository.getAllIncomeByYear(date.getYear() - 1);
            Float previousExpense = isMonth ? repository.getAllExpenseByMonth(date.getMonthValue() - 1, date.getYear()) : repository.getAllExpenseByYear(date.getYear() - 1);

            Float income = isMonth ? repository.getAllIncomeByMonth(date.getMonthValue(), date.getYear()) : repository.getAllIncomeByYear(date.getYear());
            Float expense = isMonth ? repository.getAllExpenseByMonth(date.getMonthValue(), date.getYear()) : repository.getAllExpenseByYear(date.getYear());

            FundBookResponse response = new FundBookResponse();
            response.setOpeningBalance(previousIncome - previousExpense);
            response.setAllIncome(income);
            response.setAllExpense(expense);
            response.setFundBalance(response.getOpeningBalance() + response.getAllIncome() - response.getAllExpense());
            log.info("----- End get summary -----");

            return ResponseUtils.success(response, isMonth ? "Hiển thị thông số theo tháng thành công" : "Hiển thị thông số theo năm thành công");
        }
        catch (Exception e) {
            log.info("----- Get summary failed -----\n" + e.getMessage());
            return ResponseUtils.error("Hiển thị thông số thất bại");
        }
    }

    private FundBook findFundBook(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fund Book not found with id " + id));
    }

    private Invoice findInvoice(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + id));
    }
}
