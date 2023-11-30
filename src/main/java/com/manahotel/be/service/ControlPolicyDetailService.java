package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ControlPolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ControlPolicyDetailService {
    @Autowired
    private ControlPolicyDetailRepository repository;

    @Autowired
    private ReservationDetailRepository repository2;

    @Autowired
    private PolicyDetailRepository repository3;

    public ResponseDTO getAllControlPolicyDetails() {
        return ResponseUtils.success(repository.findAll(), "Hiển thị danh sách thành công");
    }

    public ResponseDTO getControlPolicyDetailsById(String id) {
        return ResponseUtils.success(repository.findAll(), "Hiển thị chi tiết thành công");
    }

    public ResponseDTO createControlPolicy(ControlPolicyDetailDTO controlPolicyDetailDTO) {
        try {
            log.info("----- Start Create Control Policy Detail -----");

            ControlPolicyDetail controlPolicyDetail = new ControlPolicyDetail();

            commonMapping(controlPolicyDetail, controlPolicyDetailDTO);

            repository.save(controlPolicyDetail);
            log.info("----- End Create Control Policy Detail -----");

            return ResponseUtils.success(controlPolicyDetail, "Tạo thành công");
        }
        catch (Exception e) {
            log.info("------ Create Control Policy Failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thất bại");
        }
    }

    public ResponseDTO updateControlPolicy(Long id, ControlPolicyDetailDTO controlPolicyDetailDTO) {
        try {
            log.info("----- Update Create Control Policy Start -----");
            ControlPolicyDetail controlPolicyDetail = findControlPolicyDetail(id);

            commonMapping(controlPolicyDetail, controlPolicyDetailDTO);

            repository.save(controlPolicyDetail);
            log.info("----- End Update Control Policy Start -----");

            return ResponseUtils.success(controlPolicyDetail, "Cập nhật thành công");
        }
        catch (Exception e) {
            log.info("------ Update Control Policy Failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật thất bại");
        }
    }

    private void commonMapping(ControlPolicyDetail controlPolicyDetail, ControlPolicyDetailDTO controlPolicyDetailDTO) {
        ReservationDetail reservationDetail = (controlPolicyDetailDTO.getReservationDetailId() != null) ? findReservationDetail(controlPolicyDetailDTO.getReservationDetailId()) : controlPolicyDetail.getReservationDetail();
        controlPolicyDetail.setReservationDetail(reservationDetail);

        PolicyDetail policyDetail = (controlPolicyDetailDTO.getPolicyDetailId() != null) ? findPolicyDetail(controlPolicyDetailDTO.getPolicyDetailId()) : controlPolicyDetail.getPolicyDetail();
        controlPolicyDetail.setPolicyDetail(policyDetail);

        controlPolicyDetail.setTypeValue((controlPolicyDetailDTO.getTypeValue() != null) ? controlPolicyDetailDTO.getTypeValue() : controlPolicyDetail.getTypeValue());
        controlPolicyDetail.setValue((controlPolicyDetailDTO.getValue() != null) ? controlPolicyDetailDTO.getValue() : controlPolicyDetail.getValue());
        controlPolicyDetail.setDiscrepancy((controlPolicyDetailDTO.getDiscrepancy() != null) ? controlPolicyDetailDTO.getDiscrepancy() : controlPolicyDetail.getDiscrepancy());
        controlPolicyDetail.setNote((controlPolicyDetailDTO.getNote() != null) ? controlPolicyDetailDTO.getNote() : controlPolicyDetail.getNote());
    }

    private ControlPolicyDetail findControlPolicyDetail(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control Policy Detail not found with id " + id));
    }

    private ReservationDetail findReservationDetail(Long id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail not found with id " + id));
    }

    private PolicyDetail findPolicyDetail(Long id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy Detail not found with id " + id));
    }
}
