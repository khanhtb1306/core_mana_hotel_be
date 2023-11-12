package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ControlPolicyDTO;
import com.manahotel.be.model.dto.ControlPolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ControlPolicyService {

    @Autowired
    private ControlPolicyRepository repository;

    @Autowired
    private ControlPolicyDetailRepository repository2;

    @Autowired
    private ReservationRepository repository3;

    @Autowired
    private ReservationDetailRepository repository4;

    @Autowired
    private PolicyRepository repository5;

    @Autowired
    private PolicyDetailRepository repository6;

    public ResponseDTO getAllControlPolicyWithControlPolicyDetails() {
        List<Object[]> listControlPolicies = repository.findAllControlPolicyWithControlPolicyDetails();

        List<Map<String, Object>> result = new ArrayList<>();

        for(Object[] controlPolicy : listControlPolicies) {
            ControlPolicy cp = (ControlPolicy) controlPolicy[0];
            List<ControlPolicyDetail> listControlPolicyDetails = repository2.findControlPolicyDetailByControlPolicy(cp);
            Map<String, Object> detailInfo = new HashMap<>();
            detailInfo.put("controlPolicy", cp);
            detailInfo.put("listControlPolicyDetails", listControlPolicyDetails);
            result.add(detailInfo);
        }

        return ResponseUtils.success(result, "Hiển thị danh sách thành công");
    }

    public ResponseDTO getControlPolicyWithControlPolicyDetailsById(String id) {
        ControlPolicy cp = findControlPolicy(id);
        Map<String, Object> controlPolicyInfo = new HashMap<>();
        List<ControlPolicyDetail> listControlPolicyDetails = repository2.findControlPolicyDetailByControlPolicy(cp);
        controlPolicyInfo.put("controlPolicy", cp);
        controlPolicyInfo.put("listControlPolicyDetails", listControlPolicyDetails);
        return ResponseUtils.success(controlPolicyInfo, "Hiển thị chi tiết thành công");
    }

    public ResponseDTO createControlPolicy(ControlPolicyDTO controlPolicyDTO) {
        try {
            log.info("----- Start Create Control Policy Start -----");
            ControlPolicy latest = repository.findTopByOrderByControlPolicyIdDesc();
            String latestId = (latest == null) ? null : latest.getControlPolicyId();
            String nextId = IdGenerator.generateId(latestId, "CP");

            ControlPolicy controlPolicy = new ControlPolicy();
            controlPolicy.setControlPolicyId(nextId);

            commonMapping(controlPolicy, controlPolicyDTO);

            repository.save(controlPolicy);
            log.info("----- End Create Control Policy Start -----");

            return ResponseUtils.success(controlPolicy.getControlPolicyId(), "Tạo thành công");
        }
        catch (Exception e) {
            log.info("------ Create Control Policy Failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo thất bại");
        }
    }

    public ResponseDTO updateControlPolicy(String id, ControlPolicyDTO controlPolicyDTO) {
        try {
            log.info("----- Update Create Control Policy Start -----");
            ControlPolicy controlPolicy = findControlPolicy(id);

            commonMapping(controlPolicy, controlPolicyDTO);

            repository.save(controlPolicy);
            log.info("----- End Update Control Policy Start -----");

            return ResponseUtils.success(controlPolicy.getControlPolicyId(), "Cập nhật thành công");
        }
        catch (Exception e) {
            log.info("------ Update Control Policy Failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật thất bại");
        }
    }

    public ResponseDTO createUpdateControlPolicyDetail(List<ControlPolicyDetailDTO> listControlPolicyDetailDTOs) {
        try {
            ControlPolicyDetail controlPolicyDetail;

            for (ControlPolicyDetailDTO controlPolicyDetailDTO : listControlPolicyDetailDTOs) {
                if(controlPolicyDetailDTO.getControlPolicyDetailId() != null) {
                    controlPolicyDetail = findControlPolicyDetail(controlPolicyDetailDTO.getControlPolicyDetailId());

                    if(!findReservationDetail(controlPolicyDetailDTO.getReservationDetailId()).equals(controlPolicyDetail.getReservationDetail())
                    && !findControlPolicy(controlPolicyDetailDTO.getControlPolicyId()).equals(controlPolicyDetail.getControlPolicy())
                    && !findPolicyDetail(controlPolicyDetailDTO.getPolicyDetailId()).equals(controlPolicyDetail.getPolicyDetail())
                    && !controlPolicyDetailDTO.getTypeValue().equals(controlPolicyDetail.getTypeValue())
                    && !controlPolicyDetailDTO.getValue().equals(controlPolicyDetail.getValue())
                    && !controlPolicyDetailDTO.getDiscrepancy().equals(controlPolicyDetail.getDiscrepancy())
                    && !controlPolicyDetailDTO.getNote().equals(controlPolicyDetail.getNote())) {
                        commonMapping2(controlPolicyDetail, controlPolicyDetailDTO);
                        repository2.save(controlPolicyDetail);
                    }
                }
                else {
                    controlPolicyDetail = new ControlPolicyDetail();
                    commonMapping2(controlPolicyDetail, controlPolicyDetailDTO);
                    repository2.save(controlPolicyDetail);
                }
            }

            return ResponseUtils.success("Lưu thành công");
        }
        catch (Exception e) {
            return ResponseUtils.error("Lưu thất bại");
        }
    }

    private void commonMapping(ControlPolicy controlPolicy, ControlPolicyDTO controlPolicyDTO) {
        Reservation reservation = (controlPolicyDTO.getReservationId() != null) ? findReservation(controlPolicyDTO.getReservationId()) : controlPolicy.getReservation();
        controlPolicy.setReservation(reservation);

        Policy policy = (controlPolicyDTO.getPolicyId() != null) ? findPolicy(controlPolicyDTO.getPolicyId()) : controlPolicy.getPolicy();
        controlPolicy.setPolicy(policy);

        controlPolicy.setTotalPrice((controlPolicyDTO.getTotalPrice() != null) ? controlPolicyDTO.getTotalPrice() : controlPolicy.getTotalPrice());
        controlPolicy.setNote((controlPolicyDTO.getNote() != null) ? controlPolicyDTO.getNote() : controlPolicy.getNote());
    }

    private void commonMapping2(ControlPolicyDetail controlPolicyDetail, ControlPolicyDetailDTO controlPolicyDetailDTO) {
        ReservationDetail reservationDetail = (controlPolicyDetailDTO.getReservationDetailId() != null) ? findReservationDetail(controlPolicyDetailDTO.getReservationDetailId()) : controlPolicyDetail.getReservationDetail();
        controlPolicyDetail.setReservationDetail(reservationDetail);

        ControlPolicy controlPolicy = (controlPolicyDetailDTO.getControlPolicyId() != null) ? findControlPolicy(controlPolicyDetailDTO.getControlPolicyId()) : controlPolicyDetail.getControlPolicy();
        controlPolicyDetail.setControlPolicy(controlPolicy);

        PolicyDetail policyDetail = (controlPolicyDetailDTO.getPolicyDetailId() != null) ? findPolicyDetail(controlPolicyDetailDTO.getPolicyDetailId()) : controlPolicyDetail.getPolicyDetail();
        controlPolicyDetail.setPolicyDetail(policyDetail);

        controlPolicyDetail.setTypeValue((controlPolicyDetailDTO.getTypeValue() != null) ? controlPolicyDetailDTO.getTypeValue() : controlPolicyDetail.getTypeValue());
        controlPolicyDetail.setValue((controlPolicyDetailDTO.getValue() != null) ? controlPolicyDetailDTO.getValue() : controlPolicyDetail.getValue());
        controlPolicyDetail.setDiscrepancy((controlPolicyDetailDTO.getDiscrepancy() != null) ? controlPolicyDetailDTO.getDiscrepancy() : controlPolicyDetail.getDiscrepancy());
        controlPolicyDetail.setNote((controlPolicyDetailDTO.getNote() != null) ? controlPolicyDetailDTO.getNote() : controlPolicyDetail.getNote());
    }

    private ControlPolicy findControlPolicy(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control Policy not found with id " + id));
    }

    private Reservation findReservation(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    private Policy findPolicy(String id) {
        return repository5.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id " + id));
    }

    private ControlPolicyDetail findControlPolicyDetail(Long id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Control Policy Detail not found with id " + id));
    }

    private ReservationDetail findReservationDetail(Long id) {
        return repository4.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation Detail not found with id " + id));
    }

    private PolicyDetail findPolicyDetail(Long id) {
        return repository6.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy Detail not found with id " + id));
    }
}
