package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.response.PolicyDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.PolicyDetail;
import com.manahotel.be.repository.PolicyDetailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PolicyServiceTest {

    @Mock
    private PolicyDetailRepository policyDetailRepository;

    @InjectMocks
    private PolicyService policyService;

    @Test
    void createUpdatePolicyDetail() {
        // Mock data
        PolicyDetailDTO policyDetailDTO = new PolicyDetailDTO();
        policyDetailDTO.setPolicyId("samplePolicyId");

        // Mock repository behavior
        when(policyDetailRepository.getPolicyDetailByPolicyIdNotStatus6(Mockito.any())).thenReturn(Collections.singletonList(new PolicyDetail()));
        when(policyDetailRepository.save(Mockito.any())).thenReturn(new PolicyDetail());

        // Call the service method
        ResponseDTO responseDTO = policyService.createUpdatePolicyDetail(Collections.singletonList(policyDetailDTO));

        // Verify the results
        assertEquals("Lưu thất bại", responseDTO.getDisplayMessage());
    }

    @Test
    void createUpdateOnlyOnePolicyDetail() {
        // Mock data
        PolicyDetailDTO policyDetailDTO = new PolicyDetailDTO();
        policyDetailDTO.setPolicyDetailId(1L);
        policyDetailDTO.setStatus(Status.DELETE);

        // Mock repository behavior
        when(policyDetailRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(new PolicyDetail()));
        when(policyDetailRepository.save(Mockito.any())).thenReturn(new PolicyDetail());

        // Call the service method
        ResponseDTO responseDTO = policyService.createUpdateOnlyOnePolicyDetail(policyDetailDTO);

        // Verify the results
        assertEquals("Lưu thành công", responseDTO.getDisplayMessage());
    }
}