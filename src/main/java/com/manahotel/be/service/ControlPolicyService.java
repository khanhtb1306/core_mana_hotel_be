package com.manahotel.be.service;

import com.manahotel.be.common.constant.PolicyCont;
import com.manahotel.be.common.util.ControlPolicyUtils;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.NoEarlySurchargePolicyException;
import com.manahotel.be.exception.NoLateSurchargePolicyException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.CustomerDTO;
import com.manahotel.be.model.dto.ResponseDTO;
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

import java.util.List;
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

    public ResponseDTO calculateLateSurcharge(long reservationDetailId, String roomCategoryId, long lateTime, float roomPrice) {
        log.info("----- Calculate Late Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.LATER_OVERTIME_SURCHARGE, roomCategoryId);
            if(policyDetails.isEmpty()) {
                throw new NoLateSurchargePolicyException("Chính sách phụ thu trả muộn của hạng phòng " + roomCategoryId + "không tồn tại");
            }
            float surcharge = ControlPolicyUtils.calculateLateSurcharge(lateTime, roomPrice, policyDetails);

            addControlPolicy(reservationDetailId, PolicyCont.LATER_OVERTIME_SURCHARGE, "VND", surcharge, String.valueOf(lateTime), "Phụ thu trả muộn");
            log.info("----- Calculate Late Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu trả muộn thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateLateSurcharge_isFail");
        }
    }

    public ResponseDTO calculateEarlySurcharge(long reservationDetailId ,String roomCategoryId, long lateTime, float roomPrice) {
        log.info("----- Calculate Early Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.EARLIER_OVERTIME_SURCHARGE, roomCategoryId);
            if(policyDetails.isEmpty()) {
                throw new NoEarlySurchargePolicyException("Chính sách phụ thu nhận sớm của hạng phòng " + roomCategoryId + "không tồn tại");
            }
            float surcharge = ControlPolicyUtils.calculateEarlySurcharge(lateTime, roomPrice, policyDetails);
            addControlPolicy(reservationDetailId, PolicyCont.EARLIER_OVERTIME_SURCHARGE, "VND", surcharge, String.valueOf(lateTime), "Phụ thu nhận sớm");

            log.info("----- Calculate Early Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu nhận sớm thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateEarlySurcharge_isFail");
        }
    }

    public ResponseDTO calculateAdditionalAdultSurcharge(long reservationDetailId, String roomCategoryId, long totalAdult, float roomPrice, long timeUse) {
        log.info("----- Calculate Additional Adult Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.ADDITIONAL_ADULT_SURCHARGE, roomCategoryId);
            if(policyDetails.isEmpty()) {
                throw new NoLateSurchargePolicyException("Chính sách phụ thu trả muộn của hạng phòng " + roomCategoryId + "không tồn tại");
            }
            float surcharge = ControlPolicyUtils.calculateAdditionalAdultSurcharge(totalAdult, roomPrice, timeUse, policyDetails);
            addControlPolicy(reservationDetailId, PolicyCont.ADDITIONAL_ADULT_SURCHARGE, "VND", surcharge, String.valueOf(totalAdult), "Phụ thu quá nguười lớn");

            log.info("----- Calculate Additional Adult Surcharge End------");
            return ResponseUtils.success(surcharge, "Tính phụ thu quá người lớn thành công");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("calculateAdditionalAdultSurcharge_isFail");
        }
    }
    public ResponseDTO calculateAdditionalChildrenSurcharge(long reservationDetailId, String roomCategoryId, float roomPrice, List<CustomerDTO> customerDTOS, long timeUse) {
        log.info("----- Calculate Additional Children Surcharge Start------");
        try{
            List<PolicyDetail> policyDetails = policyDetailRepository.findPolicyDetailByPolicyNameAndRoomCategoryId(PolicyCont.ADDITIONAL_CHILDREN_SURCHARGE, roomCategoryId);
            if(policyDetails.isEmpty()) {
                throw new NoLateSurchargePolicyException("Chính sách trẻ em của hạng phòng " + roomCategoryId + "không tồn tại");
            }
            float surcharge = ControlPolicyUtils.calculateAdditionalChildrenSurcharge(policyDetails,customerDTOS, roomPrice, timeUse);
            addControlPolicy(reservationDetailId, PolicyCont.ADDITIONAL_CHILDREN_SURCHARGE, "VND", surcharge, String.valueOf(customerDTOS.size()), "Phụ thu trẻ em");

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

    public void addControlPolicy(Long reservationDetailId, String policyName, String typeValue, float surcharge, String discrepancy, String note){
        log.info("----- Add Info To Control Policy Start------");
        try {
            ControlPolicy controlPolicy = new ControlPolicy();
            controlPolicy.setReservationDetail(findReservationDetail(reservationDetailId));
            controlPolicy.setPolicy(policyRepository.getPolicyByPolicyName(policyName));
            controlPolicy.setTypeValue(typeValue);
            controlPolicy.setValue(surcharge);
            controlPolicy.setDiscrepancy(discrepancy);
            controlPolicy.setNote(note);
            controlPolicyRepository.save(controlPolicy);
        }catch (Exception e){
            log.error("Add Control Policy fail" + e.getMessage());
        }
        log.info("----- Add Info To Control Policy End------");
    }
}
