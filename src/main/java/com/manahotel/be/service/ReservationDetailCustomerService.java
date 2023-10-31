package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDetailCustomerDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReservationDetailCustomerService {

    @Autowired
    private ReservationDetailCustomerRepository repository;

    @Autowired
    private ReservationDetailRepository repository2;

    @Autowired
    private CustomerRepository repository3;

    public ResponseDTO createRDCustomer(ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        try {
            log.info("----- Start create RDR -----");
            ReservationDetailCustomer rdr = new ReservationDetailCustomer();

            commonMapping(rdr, reservationDetailCustomerDTO);

            repository.save(rdr);
            log.info("----- End create RDR -----");

            return ResponseUtils.success(rdr.getReservationDetailCustomerId(), "Tạo thông tin khách hàng theo phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Create RDR failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO updateRDCustomer(Long id, ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        try {
            log.info("----- Start update RDR -----");
            ReservationDetailCustomer rdr = findReservationDetailRoom(id);

            commonMapping(rdr, reservationDetailCustomerDTO);

            repository.save(rdr);
            log.info("----- End update RDR -----");

            return ResponseUtils.success(rdr.getReservationDetailCustomerId(), "Cập nhật thông tin khách hàng theo phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Update RDR failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO deleteRDCustomer(Long id) {
        try {
            log.info("----- Start delete RDR -----");
            ReservationDetailCustomer rdr = findReservationDetailRoom(id);

            Long rdrId = rdr.getReservationDetailCustomerId();

            repository.delete(rdr);
            log.info("----- End delete RDR -----");

            return ResponseUtils.success(rdrId, "Xóa thông tin khách hàng theo phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Delete RDR failed -----\n" + e.getMessage());
            return ResponseUtils.error("Xóa thông tin khách hàng theo phòng thất bại");
        }
    }

    private void commonMapping(ReservationDetailCustomer rdc, ReservationDetailCustomerDTO reservationDetailCustomerDTO) {
        ReservationDetail rd = (reservationDetailCustomerDTO.getReservationDetailId() != null) ? findReservationDetail(reservationDetailCustomerDTO.getReservationDetailId()) : rdc.getReservationDetail();
        rdc.setReservationDetail(rd);

        Customer c = (reservationDetailCustomerDTO.getCustomerId() != null) ? findCustomer(reservationDetailCustomerDTO.getCustomerId()) : rdc.getCustomer();
        rdc.setCustomer(c);
    }

    private ReservationDetailCustomer findReservationDetailRoom(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail Room not found with id " + id));
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
