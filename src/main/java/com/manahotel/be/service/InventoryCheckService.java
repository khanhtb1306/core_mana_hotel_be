package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.EmptyListException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.InventoryCheckDTO;
import com.manahotel.be.model.dto.InventoryCheckDetailDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.model.entity.InventoryCheck;
import com.manahotel.be.model.entity.InventoryCheckDetail;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import com.manahotel.be.repository.InventoryCheckDetailRepository;
import com.manahotel.be.repository.InventoryCheckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventoryCheckService {

    @Autowired
    private InventoryCheckRepository repository;

    @Autowired
    private InventoryCheckDetailRepository repository2;

    @Autowired
    private GoodsRepository repository3;

    @Autowired
    private GoodsUnitRepository repository4;

    public List<InventoryCheck> getAll() {
        return repository.findAll();
    }

    private void commonMapping(InventoryCheck check, InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {
        check.setStatus(dto.getStatus());

        if (check.getStatus().equals(Status.TEMPORARY)) {
            check.setTimeBalance(null);
        } else {
            Timestamp timeBalance = Optional.ofNullable(dto.getTimeBalance())
                    .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
            check.setTimeBalance(timeBalance);
        }

        check.setNote(dto.getNote());

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
        }

        if(!list.isEmpty()) {
            repository2.saveAll(list);
        }
        else{
            throw new EmptyListException("Chi tiết kiểm kho không được để trống");
        }
    }

    public String createInventoryCheck(InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {
        try {
            log.info("----- Add Check Start -----");
            InventoryCheck latestCheck = repository.findTopByOrderByInventoryCheckIdDesc();
            String latestId = (latestCheck == null) ? null : latestCheck.getInventoryCheckId();
            String nextId = IdGenerator.generateId(latestId, "KK");

            InventoryCheck check = new InventoryCheck();
            check.setInventoryCheckId(nextId);
            check.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            commonMapping(check, dto, listDetailDTO);
            log.info("----- Add Check End -----");

            return "Tạo kiểm kho thành công";
        }
        catch (Exception e) {
            log.info("Can't add inventory check", e.getMessage());
            return "Tạo kiểm kho thất bại";
        }
    }

    public String updateInventoryCheck(String id, InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {
        try {
            log.info("----- Update Check Start -----");
            InventoryCheck check = findInventoryCheckById(id);

            if (check == null) {
                return null;
            }

            commonMapping(check, dto, listDetailDTO);
            log.info("----- Update Check End -----");

            return "Cập nhật kiểm kho thành công";
        }
        catch (Exception e) {
            log.info("Can't update inventory check", e.getMessage());
            return "Cập nhật kiểm kho thất bại";
        }
    }

    public List<InventoryCheck> getAllCheck() {
        return repository.findAll();
    }

    public String cancelCheck(String id) {
        try {
            InventoryCheck check = findInventoryCheckById(id);
            check.setStatus(Status.CANCEL);
            repository.save(check);
            return "Hủy kiểm kho thành công";
        }
        catch (Exception e) {
            log.info("Can't update inventory check", e.getMessage());
            return "Hủy kiểm kho thất bại";
        }
    }

    public List<InventoryCheckDetail> findListInventoryCheckDetailByInventoryCheckId(String id) {
        return repository2.findListInventoryCheckDetailByInventoryCheckId(id);
    }

    public InventoryCheck findInventoryCheckById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Check not found with id " + id));
    }

    private Goods findGoods(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    public InventoryCheckResponse getInventoryCheckSummary(String id) {
        return repository2.getInventoryCheckSummary(id);
    }
}
