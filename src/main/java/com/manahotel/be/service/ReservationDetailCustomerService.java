package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.ReservationDetailCustomerDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ReservationRepository repository4;

    public ResponseDTO getListCustomersByReservationDetailId(Long reservationDetailId) {
        return ResponseUtils.success(repository.findReservationDetailCustomerByReservationDetail(findReservationDetail(reservationDetailId)), "Hiển thị thông tin khách hàng theo đơn đặt phòng thành công");
    }

    public ResponseDTO createRDCustomer(ReservationDetailCustomerDTO dto, boolean isAdult) {
        try {
            log.info("----- Start create RDC -----");
            ReservationDetailCustomer rdc = new ReservationDetailCustomer();

            commonMapping(rdc, dto);

            repository.save(rdc);
            log.info("----- End create RDC -----");

            Reservation reservation = findReservationDetail(dto.getReservationDetailId()).getReservation();

            if(isAdult) {
                reservation.setTotalAdults((reservation.getTotalAdults() != null ? reservation.getTotalAdults() : 0) + 1);
            }
            else {
                reservation.setTotalChildren((reservation.getTotalChildren() != null ? reservation.getTotalChildren() : 0) + 1);
            }

            repository4.save(reservation);

            return ResponseUtils.success("Tạo thông tin khách hàng theo phòng thành công");
        } catch (Exception e) {
            log.info("----- Create RDC failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO updateRDCustomer(ReservationDetailCustomerDTO dto, boolean check) {
        try {
            Reservation reservation = findReservationDetail(dto.getReservationDetailId()).getReservation();

            if(check) {
                reservation.setTotalAdults((reservation.getTotalAdults() != null ? reservation.getTotalAdults() : 0) - 1);
                reservation.setTotalChildren((reservation.getTotalChildren() != null ? reservation.getTotalChildren() : 0) + 1);
            }
            else {
                reservation.setTotalAdults((reservation.getTotalAdults() != null ? reservation.getTotalAdults() : 0) + 1);
                reservation.setTotalChildren((reservation.getTotalChildren() != null ? reservation.getTotalChildren() : 0) - 1);
            }

            repository4.save(reservation);

            return ResponseUtils.success("Tạo thông tin khách hàng theo phòng thành công");
        } catch (Exception e) {
            log.info("----- Create RDC failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO deleteRDCustomer(Long id, boolean isAdult) {
        try {
            log.info("----- Start delete RDC -----");
            ReservationDetailCustomer rdc = findReservationDetailCustomer(id);

            Long reservationDetailId = rdc.getReservationDetail().getReservationDetailId();

            repository.delete(rdc);

            log.info("----- End delete RDC -----");

            Reservation reservation = findReservationDetail(reservationDetailId).getReservation();

            if(isAdult) {
                reservation.setTotalAdults((reservation.getTotalAdults() != null ? reservation.getTotalAdults() : 0) - 1);
            }
            else {
                reservation.setTotalChildren((reservation.getTotalChildren() != null ? reservation.getTotalChildren() : 0) - 1);
            }

            repository4.save(reservation);

            return ResponseUtils.success("Xóa thông tin khách hàng theo phòng thành công");
        } catch (Exception e) {
            log.info("----- Delete RDC failed -----\n" + e.getMessage());
            return ResponseUtils.error("Xóa thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO checkCustomerIsVisitor(String reservationId) {
        try {
            List<ReservationDetail> reservationList = repository2.findReservationDetailByReservation_ReservationId(reservationId);
            for (ReservationDetail rd : reservationList) {
                List<ReservationDetailCustomer> rds = repository.findReservationDetailCustomerByReservationDetail_ReservationDetailId(rd.getReservationDetailId());
                for (ReservationDetailCustomer rdc : rds) {
                    Customer customer = repository3.findByCustomerId(rdc.getCustomer().getCustomerId());
                    if (customer.getIsCustomer()) {
                        return ResponseUtils.success(true, "checkCustomerIsVisitor_isSuccess");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseUtils.success(false, "checkCustomerIsVisitor_isSuccess");
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
