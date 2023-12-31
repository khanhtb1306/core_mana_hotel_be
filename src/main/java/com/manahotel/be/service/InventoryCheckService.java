package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.EmptyListException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.InventoryCheckDTO;
import com.manahotel.be.model.dto.response.InventoryCheckDetailDTO;
import com.manahotel.be.model.dto.response.InventoryCheckResponse;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@Data
public class InventoryCheckService {

    @Autowired
    private InventoryCheckRepository repository;

    @Autowired
    private InventoryCheckDetailRepository repository2;

    @Autowired
    private GoodsRepository repository3;

    @Autowired
    private GoodsUnitRepository repository4;

    @Autowired
    private StaffRepository repository5;

    @Autowired
    private OverviewService overviewService;

    public ResponseEntity<List<Map<String, Object>>> getAllInventoryCheckWithDetails() {
        List<Object[]> listInventoryChecks = repository.findAllInventoryChecksWithDetails();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] inventoryCheck : listInventoryChecks) {
            InventoryCheck ic = (InventoryCheck) inventoryCheck[0];
            List<InventoryCheckDetail> listInventoryCheckDetails = repository2.findInventoryCheckDetailByInventoryCheck(ic);
            Map<String, Object> checkInfo = new HashMap<>();
            checkInfo.put("inventoryCheck", ic);
            checkInfo.put("listInventoryCheckDetails", listInventoryCheckDetails.toArray());
            result.add(checkInfo);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private void commonMapping(InventoryCheck check, InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {
        check.setStatus(dto.getStatus() != null ? dto.getStatus() : check.getStatus());

        if (check.getStatus().equals(Status.TEMPORARY)) {
            check.setTimeBalance(null);
        } else {
            Timestamp timeBalance = Optional.ofNullable(dto.getTimeBalance() != null ? dto.getTimeBalance() : check.getTimeBalance())
                    .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
            check.setTimeBalance(timeBalance);
        }

        check.setNote(dto.getNote() != null ? dto.getNote() : check.getNote());

        repository.save(check);

        repository2.deleteInventoryCheckDetailByInventoryCheckId(check.getInventoryCheckId());

        List<InventoryCheckDetail> list = new ArrayList<>();

        for (InventoryCheckDetailDTO detailDTO : listDetailDTO) {

            InventoryCheckDetail detail = new InventoryCheckDetail();

            InventoryCheck ic = findInventoryCheckById(check.getInventoryCheckId());
            detail.setInventoryCheck(ic);

            Goods goods = findGoods(detailDTO.getGoodsId());
            detail.setGoods(goods);

            GoodsUnit goodsUnit = repository4.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true);

            detail.setActualInventory(detailDTO.getActualInventory());
            detail.setQuantityDiscrepancy(detail.getActualInventory() - goods.getInventory());
            detail.setValueDiscrepancy(detail.getQuantityDiscrepancy() * goodsUnit.getCost());
            detail.setInventory(goods.getInventory());
            detail.setCost(goodsUnit.getCost());

            list.add(detail);

            if (check.getStatus().equals(Status.BALANCE)) {
                goods.setInventory(detail.getActualInventory());
                repository3.save(goods);
            }
        }

        if (!list.isEmpty()) {
            repository2.saveAll(list);
        } else {
            throw new EmptyListException("Chi tiết kiểm kho không được để trống");
        }

        if (check.getStatus().equals(Status.BALANCE)) {
            Long userId = UserUtils.getUser().getStaffId();
            Staff staff = findStaff(userId);

            overviewService.writeRecentActivity(staff.getStaffName(), "thực hiện kiểm kho", 0, new Timestamp(System.currentTimeMillis()));
        }
    }

    public ResponseEntity<String> createInventoryCheck(InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {
        try {
            log.info("----- Add Check Start -----");
            Long userId = UserUtils.getUser().getStaffId();

            InventoryCheck latestCheck = repository.findTopByOrderByInventoryCheckIdDesc();
            String latestId = (latestCheck == null) ? null : latestCheck.getInventoryCheckId();
            String nextId = IdGenerator.generateId(latestId, "KK");

            InventoryCheck check = new InventoryCheck();
            check.setInventoryCheckId(nextId);
            check.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            check.setCreatedById(userId);

            commonMapping(check, dto, listDetailDTO);
            log.info("----- Add Check End -----");

            if (check.getStatus().equals(Status.TEMPORARY)) {
                return new ResponseEntity<>("Tạo phiếu tạm kiểm kho thành công", HttpStatus.OK);
            }

            return new ResponseEntity<>("Tạo cân bằng kiểm kho thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't add inventory check", e.getMessage());
            return new ResponseEntity<>("Tạo kiểm kho thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateInventoryCheck(String id, InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {
        try {
            log.info("----- Update Check Start -----");

            InventoryCheck check = findInventoryCheckById(id);

            commonMapping(check, dto, listDetailDTO);
            log.info("----- Update Check End -----");

            if (check.getStatus().equals(Status.TEMPORARY)) {
                return new ResponseEntity<>("Cập nhật phiếu tạm kiểm kho thành công", HttpStatus.OK);
            }

            return new ResponseEntity<>("Cập nhật cân bằng kiểm kho thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't update inventory check", e.getMessage());
            return new ResponseEntity<>("Cập nhật kiểm kho thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> cancelCheck(String id) {
        try {
            InventoryCheck check = findInventoryCheckById(id);
            check.setStatus(Status.CANCEL);
            repository.save(check);
            return new ResponseEntity<>("Hủy kiểm kho thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't update inventory check", e.getMessage());
            return new ResponseEntity<>("Hủy kiểm kho thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<InventoryCheckDetail>> findListInventoryCheckDetailByInventoryCheckId(String id) {
        return new ResponseEntity<>(repository2.findListInventoryCheckDetailByInventoryCheckId(id), HttpStatus.OK);
    }

    public InventoryCheck findInventoryCheckById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Check not found with id " + id));
    }

    private Goods findGoods(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private Staff findStaff(Long id) {
        return repository5.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
    }

    public ResponseEntity<InventoryCheckResponse> getInventoryCheckSummary(String id) {
        return new ResponseEntity<>(repository2.getInventoryCheckSummary(id), HttpStatus.OK);
    }
}
