package com.manahotel.be.service;

import com.manahotel.be.common.constant.PolicyCont;
import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.PolicyDTO;
import com.manahotel.be.model.dto.PolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.TimeUseDTO;
import com.manahotel.be.model.entity.Policy;
import com.manahotel.be.model.entity.PolicyDetail;
import com.manahotel.be.model.entity.TimeUse;
import com.manahotel.be.repository.PolicyDetailRepository;
import com.manahotel.be.repository.PolicyRepository;
import com.manahotel.be.repository.TimeUseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyDetailRepository policyDetailRepository;

    @Autowired
    private TimeUseRepository timeUseRepository;

    //TODO Setup Time USE
    public ResponseDTO getSetupTimeUse() {
            log.info("----- Setup Time Use Start ------");
        try{
            TimeUse timeUse = timeUseRepository.findTopByOrderByTimeUseId();
            TimeUse timeUses = findTimeUse(timeUse.getTimeUseId());
            log.info("----- Setup Time Use End  ------");
            return ResponseUtils.success(timeUses,"IsSuccess");
        }catch (Exception e){
            return ResponseUtils.error("GetFail");
        }
    }

    public ResponseDTO createUpdateSetupTimeUse(TimeUse timeUse, TimeUseDTO timeUseDTO) {
            log.info("----- Create Update Setup Time Use Start ------");
        try{
            TimeUse timeUses = findTimeUse(timeUse.getTimeUseId());
            commonMapping(timeUse, timeUseDTO);
            timeUseRepository.save(timeUses);
            log.info("----- Create Update Setup Time Use End ------");
            return ResponseUtils.success(timeUses,"Lưu thành công");
        }catch (Exception e){
            log.info(e.getMessage());
            return  ResponseUtils.error("Lưu thất bại");
        }

    }

    //TODO other revenue
    public ResponseDTO getAllPolicyOtherRevenue() {
        log.info("----- Get All Policy Other Revenue Start ------");
        try{
            Map<String, Object> listPolicy = new HashMap<>();
            Policy policy = policyRepository.getPoliciesByPolicyName(PolicyCont.OTHER_REVENUE);
            List<PolicyDetail> policyDetail = policyDetailRepository.getPolicyDetailByPolicyIdNotStatus6(policy.getPolicyId());
            listPolicy.put("Policy", policy);
            listPolicy.put("ListOtherRevenue ", policyDetail);
            log.info("----- Get All Policy Other Revenue End ------");
            return ResponseUtils.success(listPolicy,"IsSuccess");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("GetFail");
        }

    }

    public ResponseDTO createUpdatePolicyOtherRevenue(PolicyDetailDTO policyDetailDTO) {
        log.info("----- Create Update Policy Other Revenue Start ------");
        try {
            PolicyDetail policyDetail = new PolicyDetail();
            if (policyDetailDTO.getPolicyDetailId() != null) {
                policyDetail = findPolicyDetail(policyDetailDTO.getPolicyDetailId());
            }
            commonMapping(policyDetail, policyDetailDTO);
            policyDetailRepository.save(policyDetail);
            log.info("----- Create Update Policy Other Revenue End ------");
            return ResponseUtils.success("Lưu thành công");
        } catch (Exception e) {
            return ResponseUtils.error("Lưu thất bại");
        }
    }


    public ResponseDTO DeletePolicyOtherRevenue(Long id) {
        log.info("----- Delete Policy Other Revenue Start ------");
        try{
            PolicyDetail policyDetail = findPolicyDetail(id);
            policyDetail.setStatus(Status.DELETE);
            policyDetailRepository.save(policyDetail);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("Xóa thất bại");
        }
        log.info("----- Delete Policy Other Revenue Start ------");
        return ResponseUtils.success("Xóa thành công");
    }

    //TODO Additional Person Surcharge
    public ResponseDTO getAdditionalPersonSurcharge() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO CreateUpdateAdditionalPersonSurcharge() {

        return ResponseUtils.success("Success");
    }

    //TODO Overtime Surcharge
    public ResponseDTO getOvertimeSurcharge() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO CreateUpdateOvertimeSurcharge() {

        return ResponseUtils.success("Success");
    }

    //TODO Cancel room Surcharge
    public ResponseDTO getCancelRoomSurcharge() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO CreateUpdateCancelRoomSurcharge() {

        return ResponseUtils.success("Success");
    }

    //TODO Setup deposit
    public ResponseDTO getSetupDeposit() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO CreateUpdateSetupDeposit() {

        return ResponseUtils.success("Success");
    }

    //TODO set promotion policy
    public ResponseDTO setPromotionPolicy() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO CreateUpdatePromotionPolicy() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO GetSetupPointSystem() {

        return ResponseUtils.success("Success");
    }

    public ResponseDTO CreateUpdateSetupPointSystem() {

        return ResponseUtils.success("Success");
    }


    private void commonMapping(Policy policy, PolicyDTO dto) {
        policy.setPolicyName(dto.getPolicyName() != null ? dto.getPolicyName() : policy.getPolicyName());
        policy.setNote(dto.getNote() != null ? dto.getNote() : policy.getNote());
        policy.setStartTime(dto.getStartTime() != null ? dto.getStartTime() : policy.getStartTime());
        policy.setEndTime(dto.getEndTime() != null ? dto.getEndTime() : policy.getEndTime());
    }

    private void commonMapping(PolicyDetail policyDetail, PolicyDetailDTO dto) {
        policyDetail.setRoomCategoryId(dto.getRoomCategoryId() != null ? dto.getRoomCategoryId() : policyDetail.getRoomCategoryId());
        policyDetail.setCustomerGroup(dto.getCustomerGroup() != null ? dto.getCustomerGroup() : policyDetail.getCustomerGroup());
        policyDetail.setFrom(dto.getFrom() != null ? dto.getFrom() : policyDetail.getFrom());
        policyDetail.setTo(dto.getTo() != null ? dto.getTo() : policyDetail.getTo());
        policyDetail.setCondition(dto.getCondition() != null ? dto.getCondition() : policyDetail.getCondition());
        policyDetail.setOther(dto.getOther() != null ? dto.getOther() : policyDetail.getOther());
        policyDetail.setType(dto.getType() != null ? dto.getType() : policyDetail.getType());
        policyDetail.setTypeValue(dto.getTypeValue() != null ? dto.getTypeValue() : policyDetail.getTypeValue());
        policyDetail.setValue(dto.getValue() != null ? dto.getValue() : policyDetail.getValue());
        policyDetail.setNote(dto.getNote() != null ? dto.getNote() : policyDetail.getNote());
        policyDetail.setAutoAddToInvoice(dto.getAutoAddToInvoice());
    }

    private void commonMapping(TimeUse timeUse, TimeUseDTO dto) {
        timeUse.setTimeBonusHour(dto.getTimeBonusHour() != null ? dto.getTimeBonusHour() : timeUse.getTimeBonusHour());
        timeUse.setStartTimeNight(dto.getStartTimeNight() != null ? dto.getStartTimeNight() : timeUse.getEndTimeNight());
        timeUse.setEndTimeNight(dto.getEndTimeNight() != null ? dto.getEndTimeNight() : timeUse.getEndTimeNight());
        timeUse.setStartTimeDay(dto.getStartTimeDay() != null ? dto.getStartTimeDay() : timeUse.getEndTimeDay());
        timeUse.setEndTimeDay(dto.getEndTimeDay() != null ? dto.getEndTimeDay() : timeUse.getEndTimeDay());
        timeUse.setTimeBonusDayType(dto.getTimeBonusDayType() != null ? dto.getTimeBonusDayType() : timeUse.getTimeBonusDayType());
        timeUse.setTimeBonusDay(dto.getTimeBonusDay() != null ? dto.getTimeBonusDay() : timeUse.getTimeBonusDay());

    }

    private Policy findPolicy(String id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with id " + id));
    }

    private PolicyDetail findPolicyDetail(Long id) {
        return policyDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy Detail not found with id " + id));
    }

    private TimeUse findTimeUse(Long id) {
        return timeUseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Time User not found with id " + id));
    }


}
