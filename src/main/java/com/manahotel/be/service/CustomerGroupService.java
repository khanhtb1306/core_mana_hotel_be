package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.CustomerGroupDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.Customer;
import com.manahotel.be.model.entity.CustomerGroup;
import com.manahotel.be.repository.CustomerGroupRepository;
import com.manahotel.be.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerGroupService {

    @Autowired
    private CustomerGroupRepository customerGroupRepository;

    @Autowired
    private CustomerRepository customerRepository;
    public ResponseDTO getAll() {
        log.info("----- Get All CustomerGroup Start------");
        try {
            List<CustomerGroup> customerGroups = customerGroupRepository.findAll();

            log.info("----- Get All CustomerGroup End ------");
            return ResponseUtils.success(customerGroups,"Lấy tất cả thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lấy tất cả thất bại");
        }
    }

    public ResponseDTO createAndUpdateCustomerGroup(CustomerGroupDTO customerGroupDTO) {
        log.info("----- Create And Update CustomerGroup Start------");
        try {
            CustomerGroup customerGroup = new CustomerGroup();
            CustomerGroup latestCustomerGroup = customerGroupRepository.findTopByOrderByCustomerGroupIdDesc();
            String latestId = (latestCustomerGroup == null) ? null : latestCustomerGroup.getCustomerGroupId();
            String nextId = IdGenerator.generateId(latestId, "NK");
            if (customerGroupDTO.getCustomerGroupId() != null) {
                customerGroup = getCustomerGroupById(customerGroupDTO.getCustomerGroupId());
            }
            else{
                customerGroupDTO.setCustomerGroupId(nextId);
            }
            commonMapping(customerGroup, customerGroupDTO);
            customerGroup.setStatus(Status.ACTIVE);
            customerGroupRepository.save(customerGroup);

            log.info("----- Create And Update CustomerGroup End ------");
            return ResponseUtils.success(customerGroup.getCustomerGroupId(),"Lưu thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lưu thất bại");
        }
    }

    public ResponseDTO deleteCustomerGroup(String customerGroupId) {
        log.info("----- Delete CustomerGroup Start------");
        try {
            CustomerGroup customerGroup = getCustomerGroupById(customerGroupId);
            String status = Status.NO_ACTIVE;
            List<Customer> customers = customerRepository.findByCustomerGroup_CustomerGroupIdAndStatusIsNot(customerGroupId,status);
            if (!customers.isEmpty()){
                return ResponseUtils.error("Không thể xóa nhóm khách hàng vì nhóm khách hàng tồn tại khách hàng");
            }else {
                customerGroup.setStatus(Status.NO_ACTIVE);
            }            customerGroupRepository.save(customerGroup);
            log.info("----- Delete CustomerGroup End ------");
            return ResponseUtils.success("Xóa thành công");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Xóa thất bại");
        }
    }

    private void commonMapping(CustomerGroup customerGroup, CustomerGroupDTO customerGroupDTO) {
        customerGroup.setCustomerGroupId(customerGroupDTO.getCustomerGroupId() != null ? customerGroupDTO.getCustomerGroupId() : customerGroup.getCustomerGroupId());
        customerGroup.setCustomerGroupName(customerGroupDTO.getCustomerGroupName() != null ? customerGroupDTO.getCustomerGroupName() : customerGroup.getCustomerGroupName());
    }

    public CustomerGroup getCustomerGroupById(String id) {
        return customerGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CustomerGroup not found with id " + id));

    }

}
