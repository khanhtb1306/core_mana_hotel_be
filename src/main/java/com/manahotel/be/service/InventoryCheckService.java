package com.manahotel.be.service;

import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.InventoryCheckDTO;
import com.manahotel.be.model.dto.InventoryCheckDetailDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.InventoryCheck;
import com.manahotel.be.model.entity.InventoryCheckDetail;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.InventoryCheckDetailRepository;
import com.manahotel.be.repository.InventoryCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryCheckService {

    @Autowired
    private InventoryCheckRepository repository;

    @Autowired
    private InventoryCheckDetailRepository repository2;

    @Autowired
    private GoodsRepository repository3;

    public List<InventoryCheck> getAll() {
        return repository.findAll();
    }

    public InventoryCheck createInventoryCheck(InventoryCheckDTO dto, List<InventoryCheckDetailDTO> listDetailDTO) {

        InventoryCheck latestCheck = repository.findTopByOrderByInventoryCheckIdDesc();
        String latestId = (latestCheck == null) ? null : latestCheck.getInventoryCheckId();
        String nextId = IdGenerator.generateId(latestId, "KK");

        InventoryCheck check = new InventoryCheck();
        check.setInventoryCheckId(nextId);
        Timestamp timeBalance = Optional.ofNullable(dto.getTimeBalance())
                .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
        check.setTimeBalance(timeBalance);
        check.setNote(dto.getNote());
        check.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        check.setStatus(dto.getStatus());

        repository.save(check);

        List<InventoryCheckDetail> list = new ArrayList<>();

        for(InventoryCheckDetailDTO detailDTO : listDetailDTO) {

            InventoryCheckDetail detail = new InventoryCheckDetail();

            InventoryCheck ic = findInventoryCheckById(nextId);
            detail.setInventoryCheck(ic);

            Goods goods = findGoods(detailDTO.getGoodsId());
            detail.setGoods(goods);

            detail.setActualInventory(detailDTO.getActualInventory());
            detail.setQuantityDiscrepancy(detail.getActualInventory() - goods.getInventory());
            detail.setValueDiscrepancy(detail.getQuantityDiscrepancy() * goods.getCost());
            detail.setInventory(goods.getInventory());
            detail.setCost(goods.getCost());

            list.add(detail);
        }

        repository2.saveAll(list);

        return check;
    }

    public InventoryCheck findInventoryCheckById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory Check not found with id " + id));
    }

    private Goods findGoods(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }
}
