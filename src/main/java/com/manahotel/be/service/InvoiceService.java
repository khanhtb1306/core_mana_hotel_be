package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Invoice;
import com.manahotel.be.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Invoice

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    public ResponseDTO getAllInvoices() {
        return ResponseUtils.success(repository.findAll(), "Hiển thị danh sách hóa đơn thành công");
    }

    public ResponseDTO getInvoiceById(String id) {
        return ResponseUtils.success(findInvoice(id), "Hiển thị chi tiết hóa đơn thành công");
    }

    private Invoice findInvoice(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id " + id));
    }
}
