package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDetailCustomerDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
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

    public ResponseDTO getListCustomersByReservationDetailId(Long reservationDetailId) {
        return ResponseUtils.success(repository.findReservationDetailCustomerByReservationDetail(findReservationDetail(reservationDetailId)), "Hiển thị thông tin khách hàng theo đơn đặt phòng thành công");
    }

    public ResponseDTO createRDCustomer(ReservationDetailCustomerDTO dto) {
        try {
            log.info("----- Start create RDC -----");
            ReservationDetailCustomer rdc = new ReservationDetailCustomer();

            commonMapping(rdc, dto);

            repository.save(rdc);
            log.info("----- End create RDC -----");

            return ResponseUtils.success("Tạo thông tin khách hàng theo phòng thành công");
        } catch (Exception e) {
            log.info("----- Create RDC failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO updateRDCustomer(Long id, ReservationDetailCustomerDTO dto) {
        try {
            log.info("----- Start update RDC -----");
            ReservationDetailCustomer rdc = findReservationDetailCustomer(id);

            commonMapping(rdc, dto);

            repository.save(rdc);
            log.info("----- End update RDC -----");

            return ResponseUtils.success("Cập nhật thông tin khách hàng theo phòng thành công");
        } catch (Exception e) {
            log.info("----- Update RDC failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO deleteRDCustomer(Long id) {
        try {
            log.info("----- Start delete RDC -----");
            ReservationDetailCustomer rdc = findReservationDetailCustomer(id);

            repository.delete(rdc);

            log.info("----- End delete RDC -----");

            return ResponseUtils.success("Xóa thông tin khách hàng theo phòng thành công");
        } catch (Exception e) {
            log.info("----- Delete RDC failed -----\n" + e.getMessage());
            return ResponseUtils.error("Xóa thông tin khách hàng theo phòng thất bại");
        }
    }

    private void commonMapping(ReservationDetailCustomer rdc, ReservationDetailCustomerDTO dto) {
        rdc.setCustomer(findCustomer(dto.getCustomerId()));
        rdc.setReservationDetail(findReservationDetail(dto.getReservationDetailId()));
    }

    private ReservationDetailCustomer findReservationDetailCustomer(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail Customer not found with id " + id));
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
