package com.manahotel.be.service;

import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.PolicyDTO;
import com.manahotel.be.model.dto.PolicyDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.dto.TimeUseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
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

    @Autowired
    private RoomClassRepository roomClassRepository;

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

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

    public ResponseDTO createUpdateSetupTimeUse(TimeUseDTO timeUseDTO) {
            log.info("----- Create Update Setup Time Use Start ------");
        try{
            TimeUse timeUse = findTimeUse(timeUseDTO.getTimeUseId());
            commonMapping(timeUse, timeUseDTO);
            timeUseRepository.save(timeUse);
            log.info("----- Create Update Setup Time Use End ------");
            return ResponseUtils.success("Lưu thành công");
        }catch (Exception e){
            log.info(e.getMessage());
            return  ResponseUtils.error("Lưu thất bại");
        }

    }

    //TODO other revenue
    //TODO Additional Person Surcharge
    //TODO EARLIER_OVERTIME_SURCHARGE
    //TODO LATER_OVERTIME_SURCHARGE
    //TODO Cancel room Surcharge
    //TODO Setup deposit
    //TODO set promotion policy
    //TODO Setup Point System
    public ResponseDTO getAllPolicyDetailByNamePolicy(String PolicyName) {
        log.info("----- Get All Policy Detail By " + PolicyName + " Start ------");
        try{
            Map<String, Object> Policy = new HashMap<>();
            Policy policy = policyRepository.getPolicyByPolicyName(PolicyName);
            List<PolicyDetail> policyDetail = policyDetailRepository.getPolicyDetailByPolicyIdNotStatus6(policy.getPolicyId());
            Policy.put("Policy", policy);
            Policy.put("LIST_" + PolicyName + "_DETAIL", policyDetail);
            log.info("----- Get All Policy Detail By " + PolicyName + " End ------");
            return ResponseUtils.success(Policy,"IsSuccess");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("GetFail");
        }
    }

    public ResponseDTO createUpdatePolicyDetail(List<PolicyDetailDTO> policyDetailDTOList) {
        log.info("----- Create Update Policy Detail Start ------");
        try {
            String policyId = null;
            for (PolicyDetailDTO dto : policyDetailDTOList) {
                if (dto.getPolicyId() != null) {
                    policyId = dto.getPolicyId();
                    break;
                }
            }
            List<PolicyDetail> existingPolicyDetails = policyDetailRepository.getPolicyDetailByPolicyIdNotStatus6(policyId);
                for (PolicyDetail dbPolicyDetail : existingPolicyDetails) {
                    if (!policyDetailDTOList.stream()
                            .anyMatch(dto -> dto.getPolicyDetailId() != null
                                    && dto.getPolicyDetailId().equals(dbPolicyDetail.getPolicyDetailId()))) {
                        dbPolicyDetail.setStatus(6L);
                        policyDetailRepository.save(dbPolicyDetail);
                        log.info("Delete policy detail success" + dbPolicyDetail.getPolicyDetailId());
                    }
                }
            // Create or update new policy details
            for (PolicyDetailDTO dto : policyDetailDTOList) {
                PolicyDetail policyDetail;

                if (dto.getPolicyDetailId() != null) {
                    policyDetail = findPolicyDetail(dto.getPolicyDetailId());

                    if(dto.getType() != policyDetail.getType() || dto.getUnit() != policyDetail.getUnit() || dto.getLimitValue() != policyDetail.getLimitValue()
                            ||dto.getTypeValue() != policyDetail.getTypeValue() || dto.getPolicyValue() != policyDetail.getPolicyValue()
                            ||dto.getRequirement() != policyDetail.getRequirement() || dto.getOther() != policyDetail.getOther()
                            ||dto.getNote() != policyDetail.getNote() || dto.getAutoAddToInvoice() != policyDetail.getAutoAddToInvoice())
                    {
                        commonMapping(policyDetail, dto);
                        policyDetailRepository.save(policyDetail);
                        log.info("Update policyDetail success" + policyDetail.getPolicyDetailId());
                    }
                } else {
                    policyDetail = new PolicyDetail();
                    policyDetail.setStatus(1L);
                    commonMapping(policyDetail, dto);
                    policyDetailRepository.save(policyDetail);
                    log.info("Create policyDetail success" + policyDetail.getPolicyDetailId());
                }
            }

            log.info("----- Create Update Policy Detail End ------");
            return ResponseUtils.success("Lưu thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lưu thất bại");
        }
    }


    public ResponseDTO createUpdateOnlyOnePolicyDetail(PolicyDetailDTO dto) {
        log.info("----- Create Update Only One Policy Detail Start ------");
        try {
            if (dto.getPolicyDetailId() != null) {
                PolicyDetail pd = findPolicyDetail(dto.getPolicyDetailId());
                commonMapping(pd, dto);
                policyDetailRepository.save(pd);
                log.info("Update policyDetail success" + pd.getPolicyDetailId());
            } else {
                PolicyDetail pd = new PolicyDetail();
                pd.setStatus(1L);
                commonMapping(pd, dto);
                policyDetailRepository.save(pd);
                log.info("Create policyDetail success" + pd.getPolicyDetailId());
            }
            log.info("----- Create Update Only One Policy Detail End ------");
            return ResponseUtils.success("Lưu thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lưu thất bại");
        }
    }

    private void commonMapping(Policy policy, PolicyDTO dto) {
        policy.setPolicyName(dto.getPolicyName() != null ? dto.getPolicyName() : policy.getPolicyName());
        policy.setNote(dto.getNote() != null ? dto.getNote() : policy.getNote());
        policy.setStartTime(dto.getStartTime() != null ? dto.getStartTime() : policy.getStartTime());
        policy.setEndTime(dto.getEndTime() != null ? dto.getEndTime() : policy.getEndTime());
    }

    private void commonMapping(PolicyDetail policyDetail, PolicyDetailDTO dto) {
        Policy policy = (dto.getPolicyId() != null) ? findPolicy(dto.getPolicyId()) : policyDetail.getPolicy();
        policyDetail.setPolicy(policy);
        RoomCategory roomCategory = (dto.getRoomCategoryId() != null) ? findRoomCategory(dto.getRoomCategoryId()) : policyDetail.getRoomCategory();
        policyDetail.setRoomCategory(roomCategory);
        CustomerGroup customerGroup = (dto.getCustomerGroupId() !=null) ? findCustomerGroup(dto.getCustomerGroupId()) : policyDetail.getCustomerGroup();
        policyDetail.setCustomerGroup(customerGroup);
        policyDetail.setLimitValue(dto.getLimitValue() != null ? dto.getLimitValue() : policyDetail.getLimitValue());
        policyDetail.setRequirement(dto.getRequirement() != null ? dto.getRequirement() : policyDetail.getRequirement());
        policyDetail.setOther(dto.getOther() != null ? dto.getOther() : policyDetail.getOther());
        policyDetail.setType(dto.getType() != null ? dto.getType() : policyDetail.getType());
        policyDetail.setTypeValue(dto.getTypeValue() != null ? dto.getTypeValue() : policyDetail.getTypeValue());
        policyDetail.setPolicyValue(dto.getPolicyValue() != null ? dto.getPolicyValue() : policyDetail.getPolicyValue());
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
                .orElseThrow(() -> new ResourceNotFoundException("Time Use not found with id " + id));
    }

    private RoomCategory findRoomCategory(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room Class not found with id " + id));
    }

    private CustomerGroup findCustomerGroup(String id) {
        return customerGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room Class not found with id " + id));
    }
}
