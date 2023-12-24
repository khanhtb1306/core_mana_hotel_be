package com.manahotel.be.service;

import com.manahotel.be.model.entity.ControlPolicy;
import com.manahotel.be.model.entity.Policy;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.repository.ControlPolicyRepository;
import com.manahotel.be.repository.PolicyRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ControlPolicyServiceTest {

    @Mock
    private ControlPolicyRepository controlPolicyRepository;

    @Mock
    private PolicyRepository policyRepository;

    @Mock
    private ReservationDetailRepository reservationDetailRepository;

    @InjectMocks
    private ControlPolicyService controlPolicyService;

    @Test
    void addControlPolicy() {
        // Mock data
        Long reservationDetailId = 1L;
        String policyName = "LATE_CHECKOUT_SURCHARGE";
        String typeValue = "VND";
        float surcharge = 50.0F;
        String discrepancy = "30";
        String note = "Late checkout surcharge";
        boolean status = true;

        // Mock repository behavior
        when(controlPolicyRepository.findByReservationDetailIdAndPolicyIdUpdate(Mockito.anyLong(), Mockito.anyString())).thenReturn(null);
        when(reservationDetailRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ReservationDetail()));
        when(policyRepository.getPolicyByPolicyName(Mockito.anyString())).thenReturn(new Policy());
        when(controlPolicyRepository.save(any(ControlPolicy.class))).thenReturn(new ControlPolicy());

        // Call the service method
        controlPolicyService.addControlPolicy(reservationDetailId, policyName, typeValue, surcharge, discrepancy, note, status);

        // Verify the repository interactions
        Mockito.verify(controlPolicyRepository, Mockito.times(1)).save(any(ControlPolicy.class));
    }
}