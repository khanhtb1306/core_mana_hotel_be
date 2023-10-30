package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ReservationDetailRoomDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailRoom;
import com.manahotel.be.repository.CustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationDetailRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReservationDetailRoomService {

    @Autowired
    private ReservationDetailRoomRepository repository;

    @Autowired
    private ReservationDetailRepository repository2;

    @Autowired
    private CustomerRepository repository3;

    public ResponseDTO createRDRoom(ReservationDetailRoomDTO reservationDetailRoomDTO) {
        try {
            log.info("----- Start create RDR -----");
            ReservationDetailRoom rdr = new ReservationDetailRoom();

            commonMapping(rdr, reservationDetailRoomDTO);

            repository.save(rdr);
            log.info("----- End create RDR -----");

            return ResponseUtils.success(rdr.getReservationDetailRoomId(), "Tạo thông tin khách hàng theo phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Create RDR failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO updateRDRoom(Long id, ReservationDetailRoomDTO reservationDetailRoomDTO) {
        try {
            log.info("----- Start update RDR -----");
            ReservationDetailRoom rdr = findReservationDetailRoom(id);

            commonMapping(rdr, reservationDetailRoomDTO);

            repository.save(rdr);
            log.info("----- End update RDR -----");

            return ResponseUtils.success(rdr.getReservationDetailRoomId(), "Cập nhật thông tin khách hàng theo phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Update RDR failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật thông tin khách hàng theo phòng thất bại");
        }
    }

    public ResponseDTO deleteRDRoom(Long id) {
        try {
            log.info("----- Start delete RDR -----");
            ReservationDetailRoom rdr = findReservationDetailRoom(id);

            Long rdrId = rdr.getReservationDetailRoomId();

            repository.delete(rdr);
            log.info("----- End delete RDR -----");

            return ResponseUtils.success(rdrId, "Xóa thông tin khách hàng theo phòng thành công");
        }
        catch (Exception e) {
            log.info("----- Delete RDR failed -----\n" + e.getMessage());
            return ResponseUtils.error("Xóa thông tin khách hàng theo phòng thất bại");
        }
    }

    private void commonMapping(ReservationDetailRoom rdr, ReservationDetailRoomDTO reservationDetailRoomDTO) {
        ReservationDetail rd = (reservationDetailRoomDTO.getReservationDetailId() != null) ? findReservationDetail(reservationDetailRoomDTO.getReservationDetailId()) : rdr.getReservationDetail();
        rdr.setReservationDetail(rd);

        Customer c = (reservationDetailRoomDTO.getCustomerId() != null) ? findCustomer(reservationDetailRoomDTO.getCustomerId()) : rdr.getCustomer();
        rdr.setCustomer(c);
    }

    private ReservationDetailRoom findReservationDetailRoom(Long id) {
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
