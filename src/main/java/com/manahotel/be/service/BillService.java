package com.manahotel.be.service;

import com.manahotel.be.model.dto.BillDTO;
import com.manahotel.be.model.entity.OrderDetail;
import com.manahotel.be.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

//    public List<BillDTO> getBillDTO(String orderId) {
//        List<BillDTO> bill = new ArrayList<>();
//        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrder_OrderId(orderId);
//
//    }
}
