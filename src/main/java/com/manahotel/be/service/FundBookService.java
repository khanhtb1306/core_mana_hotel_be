package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.FundBookDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.FundBook;
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
            FundBook fundBook = new FundBook();
            fundBook.setFundBookId(fundBookDTO.getFundBookId());
            fundBook.setOrderId(fundBook.getOrderId());

            Timestamp time = Optional.ofNullable(fundBookDTO.getTime())
                    .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
            fundBook.setTime(time);

            fundBook.setType(fundBookDTO.getType());
            fundBook.setPaidMethod(fundBook.getPaidMethod());
            fundBook.setValue(fundBookDTO.getValue());
            fundBook.setPrepaid(fundBookDTO.getPrepaid());
            fundBook.setPaid(fundBookDTO.getPaid());
            fundBook.setPayer(fundBookDTO.getPayer());
            fundBook.setStaff(fundBookDTO.getStaff());
            fundBook.setNote(fundBookDTO.getNote());
            fundBook.setStatus(fundBookDTO.getStatus());

            repository.save(fundBook);
            log.info("----- End create fund book -----");

            return ResponseUtils.success("Tạo phiếu thành công");
        }
        catch (Exception e) {
            log.info("----- Create fund book failed -----\n" + e.getMessage());
            return ResponseUtils.success("Tạo phiếu thất bại");
        }
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
            return ResponseUtils.success("Cập nhật phiếu thất bại");
        }
    }

    private FundBook findFundBook(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fund Book not found with id " + id));
    }
}
