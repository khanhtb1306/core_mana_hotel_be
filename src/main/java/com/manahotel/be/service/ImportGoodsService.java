package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.EmptyListException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ImportGoodsDTO;
import com.manahotel.be.model.dto.ImportGoodsDetailDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
public class ImportGoodsService {

    @Autowired
    private ImportGoodsRepository repository;

    @Autowired
    private ImportGoodsDetailRepository repository2;

    @Autowired
    private GoodsRepository repository3;

    @Autowired
    private GoodsUnitRepository repository4;

    @Autowired
    private StaffRepository repository5;

    @Autowired
    private FundBookRepository repository6;

    @Autowired
    private OverviewService overviewService;

    public ResponseDTO getAllImportGoodsWithDetails() {
        List<Object[]> listImportGoods = repository.findAllImportGoodsWithDetails();

        List<Map<String, Object>> result = new ArrayList<>();

        for(Object[] importGoods : listImportGoods) {
            ImportGoods ig = (ImportGoods) importGoods[0];
            List<ImportGoodsDetail> listImportGoodsDetail = repository2.findImportGoodsDetailByImportGoods(ig);
            Map<String, Object> importInfo = new HashMap<>();
            importInfo.put("importGoods", ig);
            importInfo.put("listImportGoodsDetail", listImportGoodsDetail.toArray());
            result.add(importInfo);
        }

        return ResponseUtils.success(result, "Hiển thị thông tin nhập hàng thành công");
    }

    public ResponseDTO createImportGoods(ImportGoodsDTO importGoodsDTO, List<ImportGoodsDetailDTO> listDetailDTO) {
        try {
            log.info("----- Start create import goods -----");
            ImportGoods latestImport = repository.findTopByOrderByImportGoodsIdDesc();
            String latestId = (latestImport == null) ? null : latestImport.getImportGoodsId();
            String nextId = IdGenerator.generateId(latestId, "PN");

            Long userId = UserUtils.getUser().getStaffId();

            ImportGoods importGoods = new ImportGoods();
            importGoods.setImportGoodsId(nextId);
            importGoods.setCreatedById(userId);

            commonMapping(importGoods, importGoodsDTO, listDetailDTO);
            log.info("----- End create import goods -----");

            return ResponseUtils.success("Tạo phiếu nhập thành công");
        }
        catch (Exception e) {
            log.info("----- Create import goods failed -----\n" + e.getMessage());
            return ResponseUtils.error("Tạo phiếu nhập thất bại");
        }
    }

    public ResponseDTO updateImportGoods(String id, ImportGoodsDTO importGoodsDTO, List<ImportGoodsDetailDTO> listDetailDTO) {
        try {
            log.info("----- Start update import goods -----");
            ImportGoods importGoods = findImportGoods(id);

            commonMapping(importGoods, importGoodsDTO, listDetailDTO);
            log.info("----- End update import goods -----");

            return ResponseUtils.success("Cập nhật phiếu nhập thành công");
        }
        catch (Exception e) {
            log.info("----- Update import goods failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật phiếu nhập thất bại");
        }
    }

    private void commonMapping(ImportGoods importGoods, ImportGoodsDTO importGoodsDTO, List<ImportGoodsDetailDTO> listDetailDTO) {
        importGoods.setStatus(importGoodsDTO.getStatus() != null ? importGoodsDTO.getStatus() : importGoods.getStatus());

        if (importGoods.getStatus().equals(Status.TEMPORARY)) {
            importGoods.setTimeImport(null);
        } else {
            Timestamp timeImport = Optional.ofNullable(importGoodsDTO.getImportGoodsId() != null ? importGoodsDTO.getTimeImport() : importGoods.getTimeImport())
                    .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
            importGoods.setTimeImport(timeImport);
        }

        importGoods.setSupplier(importGoodsDTO.getSupplier() != null ? importGoodsDTO.getSupplier() : importGoods.getSupplier());
        importGoods.setPaid(importGoodsDTO.getPaid() != null ? importGoodsDTO.getPaid() : importGoods.getPaid());

        repository.save(importGoods);

        repository2.deleteImportGoodsDetailByImportGoodsId(importGoods.getImportGoodsId());

        List<ImportGoodsDetail> list = new ArrayList<>();

        for(ImportGoodsDetailDTO importGoodsDetailDTO : listDetailDTO) {
            ImportGoodsDetail importGoodsDetail = new ImportGoodsDetail();

            ImportGoods ig = findImportGoods(importGoods.getImportGoodsId());
            importGoodsDetail.setImportGoods(ig);

            Goods goods = (importGoodsDetailDTO.getGoodsId() != null) ? findGoods(importGoodsDetailDTO.getGoodsId()) : importGoodsDetail.getGoods();
            importGoodsDetail.setGoods(goods);

            GoodsUnit goodsUnit = repository4.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true);

            importGoodsDetail.setAmount(importGoodsDetailDTO.getAmount() != null ? importGoodsDetailDTO.getAmount() : importGoodsDetail.getAmount());
            importGoodsDetail.setCost(goodsUnit.getCost());
            importGoodsDetail.setTotal(importGoodsDetail.getCost() * importGoodsDetail.getAmount());

            list.add(importGoodsDetail);

            if(importGoods.getStatus().equals(Status.IMPORT)) {
                goods.setInventory(goods.getInventory() + importGoodsDetail.getAmount());
                repository3.save(goods);
            }
        }

        if (!list.isEmpty()) {
            repository2.saveAll(list);
        } else {
            throw new EmptyListException("Chi tiết phiếu nhập không được để trống");
        }

        Float sumTotal = repository2.getSumOfImportGoodsDetails(importGoods);
        importGoods.setPrice(sumTotal);

        repository.save(importGoods);

        if(importGoods.getStatus().equals(Status.IMPORT)) {
            Long userId = UserUtils.getUser().getStaffId();
            Staff staff = findStaff(userId);

            overviewService.writeRecentActivity(staff.getUsername(), "tạo phiếu nhập hàng", importGoods.getPrice(), new Timestamp(System.currentTimeMillis()));

            FundBook fundBook = new FundBook();
            fundBook.setFundBookId("TT" + importGoods.getImportGoodsId());
            fundBook.setOrderId(importGoods.getImportGoodsId());
            fundBook.setTime(new Timestamp(System.currentTimeMillis()));
            fundBook.setType(Status.EXPENSE);
            fundBook.setPaidMethod(null);
            fundBook.setValue(importGoods.getPrice());
            fundBook.setPrepaid(0F);
            fundBook.setPaid(importGoods.getPrice());
            fundBook.setPayer(importGoods.getSupplier());
            fundBook.setStaff(staff.getStaffName());
            fundBook.setNote("Chi tiền trả nhà cung cấp");
            fundBook.setStatus(Status.COMPLETE);
            repository6.save(fundBook);
        }
    }

    public ResponseDTO cancelImportGoods(String id) {
        try {
            log.info("----- Start cancel import goods -----");
            ImportGoods importGoods = findImportGoods(id);
            importGoods.setStatus(Status.CANCEL);
            repository.save(importGoods);
            log.info("----- End cancel import goods -----");

            return ResponseUtils.success("Hủy nhập hàng thành công");
        }
        catch (Exception e) {
            log.info("----- Cancel import goods failed -----\n" + e.getMessage());
            return ResponseUtils.error("Hủy nhập hàng thất bại");
        }
    }

    private ImportGoods findImportGoods(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import Goods not found with id " + id));
    }

    private Goods findGoods(String id) {
        return repository3.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private Staff findStaff(Long id) {
        return repository5.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
    }
}
