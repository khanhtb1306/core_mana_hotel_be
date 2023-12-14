package com.manahotel.be.service;

import com.manahotel.be.common.constant.PolicyCont;
import com.manahotel.be.common.util.ControlPolicyUtils;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.NoEarlySurchargePolicyException;
import com.manahotel.be.exception.NoLateSurchargePolicyException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.CustomerDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.ControlPolicy;
import com.manahotel.be.model.entity.PolicyDetail;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.repository.ControlPolicyRepository;
import com.manahotel.be.repository.PolicyDetailRepository;
import com.manahotel.be.repository.PolicyRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
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
    private ControlPolicyRepository controlPolicyRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyDetailRepository policyDetailRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    public ResponseDTO getControlPolicyByReservation(String reservationId){
        try {
            log.info("----- get Control Policy By Reservation Start------");
            List<ReservationDetail> reservationDetails = reservationDetailRepository.findReservationDetailByReservation_ReservationId(reservationId);
            Map<String, Object> controlPoliciesInfo = new HashMap<>();
            List<Object> listControlPolicyByReservation = new ArrayList<>();
            for (ReservationDetail rD : reservationDetails) {
                List<ControlPolicy> controlPolicies = controlPolicyRepository.findControlPolicyByReservationDetail_ReservationDetailId(rD.getReservationDetailId());
                controlPoliciesInfo.put("reservationDetail", rD);
                controlPoliciesInfo.put("controlPolicies", controlPolicies);
                listControlPolicyByReservation.add(controlPolicies);
            }
            log.info("----- get Control Policy By Reservation End------");
            return ResponseUtils.success(listControlPolicyByReservation, "getControlPolicyByReservation_isSuccessfully");
        }catch (Exception e){
            log.error("getControlPolicyByReservation_isFail" + e.getMessage());
            return ResponseUtils.success("getControlPolicyByReservation_isFail");
        }
    }
    public ResponseDTO getControlPolicyByReservationDetail(long reservationDetailId, String policyName){
        log.info("----- get Control Policy By Reservation Detail Start------");
        try{
            ControlPolicy controlPolicy = controlPolicyRepository.findByReservationDetailIdAndPolicyId(reservationDetailId, policyRepository.getPolicyByPolicyName(policyName).getPolicyId());
            log.info("getControlPolicy_isSuccessfully");
            log.info("----- get Control Policy By Reservation Detail End------");
            return ResponseUtils.success(controlPolicy,"getControlPolicy_isSuccessfully");
        }catch (Exception e){
            log.error("getControlPolicy_isFail");
            return ResponseUtils.error("getControlPolicy_isFail");
        }
    }

    public ResponseDTO calculateLateSurcharge(long reservationDetailId, String roomCategoryId, long lateTime, float roomPrice, boolean status) {
        log.info("----- Calculate Late Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.LATER_OVERTIME_SURCHARGE, roomCategoryId);
            float surcharge = 0;
            if(policyDetails.isEmpty()) {
                surcharge = ControlPolicyUtils.calculateLateSurcharge(lateTime, roomPrice, policyDetails);
                addControlPolicy(reservationDetailId, PolicyCont.LATER_OVERTIME_SURCHARGE, "VND", surcharge, String.valueOf(lateTime), "Phụ thu trả muộn", status);
            }

            log.info("----- Calculate Late Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu trả muộn thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateLateSurcharge_isFail");
        }
    }

    public ResponseDTO calculateEarlySurcharge(long reservationDetailId ,String roomCategoryId, long lateTime, float roomPrice, boolean status) {
        log.info("----- Calculate Early Surcharge Start------");
        try {
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.EARLIER_OVERTIME_SURCHARGE, roomCategoryId);
            float surcharge = 0;
            if (!policyDetails.isEmpty()) {
                surcharge = ControlPolicyUtils.calculateEarlySurcharge(lateTime, roomPrice, policyDetails);
                addControlPolicy(reservationDetailId, PolicyCont.EARLIER_OVERTIME_SURCHARGE, "VND", surcharge, String.valueOf(lateTime), "Phụ thu nhận sớm", status);
            }
            log.info("----- Calculate Early Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu nhận sớm thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateEarlySurcharge_isFail");
        }
    }

    public ResponseDTO calculateAdditionalAdultSurcharge(long reservationDetailId, String roomCategoryId, long totalAdult, float roomPrice, long timeUse, boolean status) {
        log.info("----- Calculate Additional Adult Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.ADDITIONAL_ADULT_SURCHARGE, roomCategoryId);
            float surcharge = 0;
            if(!policyDetails.isEmpty()) {
                surcharge = ControlPolicyUtils.calculateAdditionalAdultSurcharge(totalAdult, roomPrice, timeUse, policyDetails);
                addControlPolicy(reservationDetailId, PolicyCont.ADDITIONAL_ADULT_SURCHARGE, "VND", surcharge, String.valueOf(totalAdult), "Phụ thu quá nguười lớn", status);
            }
            log.info("----- Calculate Additional Adult Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu quá người lớn thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateAdditionalAdultSurcharge_isFail");
        }
    }
    public ResponseDTO calculateAdditionalChildrenSurcharge(long reservationDetailId, String roomCategoryId, float roomPrice, List<CustomerDTO> customerDTOS, long timeUse, boolean status) {
        log.info("----- Calculate Additional Children Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.ADDITIONAL_CHILDREN_SURCHARGE, roomCategoryId);
            float surcharge = 0;
            if(!policyDetails.isEmpty()) {
                surcharge = ControlPolicyUtils.calculateAdditionalChildrenSurcharge(policyDetails,customerDTOS, roomPrice, timeUse);
                addControlPolicy(reservationDetailId, PolicyCont.ADDITIONAL_CHILDREN_SURCHARGE, "VND", surcharge, String.valueOf(customerDTOS.size()), "Phụ thu trẻ em", status);
            }

            log.info("----- Calculate Additional Children Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu trẻ em thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateAdditionalChildrenSurcharge_isFail");
        }
    }

    private ReservationDetail findReservationDetail(Long id) {
        return reservationDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation detail not found with id " + id));
    }

    public void addControlPolicy(Long reservationDetailId, String policyName, String typeValue, float surcharge, String discrepancy, String note, boolean status){
        log.info("----- Add or Update Info To Control Policy Start------");
        try {
            ControlPolicy controlPolicy = controlPolicyRepository.findByReservationDetailIdAndPolicyIdUpdate(reservationDetailId, policyRepository.getPolicyByPolicyName(policyName).getPolicyId());
            if(controlPolicy != null){
                controlPolicy.setValue(surcharge);
                controlPolicy.setDiscrepancy(discrepancy);
                controlPolicy.setStatus(status);
                log.info("Update ");
            }else {
            controlPolicy = new ControlPolicy();
            controlPolicy.setReservationDetail(findReservationDetail(reservationDetailId));
            controlPolicy.setPolicy(policyRepository.getPolicyByPolicyName(policyName));
            controlPolicy.setTypeValue(typeValue);
            controlPolicy.setValue(surcharge);
            controlPolicy.setDiscrepancy(discrepancy);
            controlPolicy.setNote(note);
            controlPolicy.setStatus(true);
                log.info("Add ");
            }
            controlPolicyRepository.save(controlPolicy);
            log.info("Control policy successfully");
        }catch (Exception e){
            log.error("Control Policy fail" + e.getMessage());
        }
        log.info("----- Add or Update Info To Control Policy End------");
    }
}
