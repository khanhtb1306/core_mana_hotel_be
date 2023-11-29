package com.manahotel.be.service;

import com.manahotel.be.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;
}
