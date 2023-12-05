package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.FundBookDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.FundBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Slf4j
@Service
public class FundBookService {

    @Autowired
    private FundBookRepository repository;

    public ResponseDTO getAll() {
        return ResponseUtils.success(repository.findAll(), "Hiển thị danh sách sổ quỹ thành công");
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

    public void writeFundBook(Invoice invoice){
        log.info("----- Write Fund Book Start  -----");
        try{
            FundBook fundBook = new FundBook();
            fundBook.setFundBookId("TT" + invoice.getInvoiceId());
            fundBook.setInvoice(invoice);
            fundBook.setTime(new Timestamp(System.currentTimeMillis()));
            fundBook.setType(Status.INCOME);
            fundBook.setPaidMethod(invoice.getPaidMethod());
            fundBook.setValue(invoice.getTotal() - invoice.getDiscount() + invoice.getPriceOther());
            fundBook.setPrepaid(0F);
            fundBook.setPaid(invoice.getTotal() - invoice.getDiscount() + invoice.getPriceOther());
            fundBook.setPayerReceiver("Khách Hàng");
            fundBook.setStaff(UserUtils.getUser().getStaffName());
            fundBook.setNote("Thu tiền khách trả");
            fundBook.setStatus(Status.COMPLETE);
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

    private FundBook findFundBook(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fund Book not found with id " + id));
    }
}
