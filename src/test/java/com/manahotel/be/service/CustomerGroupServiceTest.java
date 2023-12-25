package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.response.CustomerGroupDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.CustomerGroup;
import com.manahotel.be.repository.CustomerGroupRepository;
import com.manahotel.be.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerGroupServiceTest {

    @Mock
    private CustomerGroupRepository customerGroupRepository;

    @InjectMocks
    private CustomerGroupService customerGroupService;

    @Test
    void createAndUpdateCustomerGroup() {
        // Mock data
        CustomerGroupDTO customerGroupDTO = new CustomerGroupDTO();
        customerGroupDTO.setCustomerGroupName("New Group");

        // Mock repository behavior
        when(customerGroupRepository.findTopByOrderByCustomerGroupIdDesc()).thenReturn(new CustomerGroup());
        when(customerGroupRepository.save(any(CustomerGroup.class))).thenReturn(new CustomerGroup());

        // Call the service method
        ResponseDTO responseDTO = customerGroupService.createAndUpdateCustomerGroup(customerGroupDTO);

        // Verify the results
        assertEquals("Lưu thành công", responseDTO.getDisplayMessage());
    }

    @Test
    void deleteCustomerGroup() {
        // Mock data
        String customerGroupId = "1";
        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.setCustomerGroupId(customerGroupId);
        customerGroup.setCustomerGroupName("Group 1");
        customerGroup.setStatus(Status.NO_ACTIVE);

        // Mock repository behavior for customerGroupRepository
        when(customerGroupRepository.findById(customerGroupId)).thenReturn(Optional.of(customerGroup));
        when(customerGroupRepository.save(any(CustomerGroup.class))).thenReturn(customerGroup);

        // Mock repository behavior for customerRepository
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findByCustomerGroup_CustomerGroupIdAndStatusIsNot(eq(customerGroupId), anyString()))
                .thenReturn(Collections.emptyList()); // Assuming you want to mock an empty list for the test

        // Create an instance of the service and set the mocked repository
        CustomerGroupService customerGroupService = new CustomerGroupService(customerGroupRepository, customerRepository);

        // Call the service method
        ResponseDTO responseDTO = customerGroupService.deleteCustomerGroup(customerGroupId);

        // Verify the results
        assertEquals("Xóa thành công", responseDTO.getDisplayMessage());
    }
}