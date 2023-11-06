package com.manahotel.be.service;

import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationDetailCustomerService {

    @Autowired
    private ReservationDetailCustomerRepository repository;

    @Autowired
    private ReservationDetailRepository repository2;

    @Autowired
    private CustomerRepository repository3;

    public String createRDCustomer(List<String> customerIds, Long reservationDetailId) {
        try {
            log.info("----- Start create RDC -----");
            ReservationDetailCustomer rdc = new ReservationDetailCustomer();

            commonMapping(customerIds, reservationDetailId);

            repository.save(rdc);
            log.info("----- End create RDC -----");

            return "Tạo thông tin khách hàng theo phòng thành công";
        }
        catch (Exception e) {
            log.info("----- Create RDC failed -----\n" + e.getMessage());
            return "Tạo thông tin khách hàng theo phòng thất bại";
        }
    }

    private void commonMapping(List<String> customerIds, Long reservationDetailId) {
        repository.deleteReservationDetailCustomerByReservationDetailId(reservationDetailId);

        for(String customerId : customerIds) {
            ReservationDetailCustomer rdc = new ReservationDetailCustomer();
            rdc.setCustomer(findCustomer(customerId));
            rdc.setReservationDetail(findReservationDetail(reservationDetailId));
        }
    }

    private ReservationDetail findReservationDetail(Long id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail not found with id " + id));
    }

    private Customer findCustomer(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }
}
