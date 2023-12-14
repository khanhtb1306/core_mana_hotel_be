package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.common.util.UserUtils;
import com.manahotel.be.exception.EmptyListException;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.ImportGoodsDTO;
import com.manahotel.be.model.dto.response.ImportGoodsDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class ImportGoodsService {

    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    @Autowired
    private ImportGoodsDetailRepository importGoodsDetailRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsUnitRepository goodsUnitRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private FundBookRepository fundBookRepository;

    @Autowired
    private OverviewService overviewService;

    public ResponseDTO getAllImportGoodsWithDetails() {
        List<Object[]> listImportGoods = importGoodsRepository.findAllImportGoodsWithDetails();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] importGoods : listImportGoods) {
            ImportGoods ig = (ImportGoods) importGoods[0];
            List<ImportGoodsDetail> listImportGoodsDetail = importGoodsDetailRepository.findImportGoodsDetailByImportGoods(ig);
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
            ImportGoods latestImport = importGoodsRepository.findTopByOrderByImportGoodsIdDesc();
            String latestId = (latestImport == null) ? null : latestImport.getImportGoodsId();
            String nextId = IdGenerator.generateId(latestId, "PN");

            Long userId = UserUtils.getUser().getStaffId();

            ImportGoods importGoods = new ImportGoods();
            importGoods.setImportGoodsId(nextId);
            importGoods.setCreatedById(userId);

            commonMapping(importGoods, importGoodsDTO, listDetailDTO);
            log.info("----- End create import goods -----");

            return ResponseUtils.success("Tạo phiếu nhập thành công");
        } catch (Exception e) {
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
        } catch (Exception e) {
            log.info("----- Update import goods failed -----\n" + e.getMessage());
            return ResponseUtils.error("Cập nhật phiếu nhập thất bại");
        }
    }



    private void commonMapping(ImportGoods importGoods, ImportGoodsDTO importGoodsDTO, List<ImportGoodsDetailDTO> listDetailDTO) {

        importGoods.setStatus(importGoodsDTO.getStatus() != null ? importGoodsDTO.getStatus() : importGoods.getStatus());

        if (importGoods.getStatus().equals(Status.TEMPORARY)) {
            importGoods.setTimeImport(null);
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(importGoodsDTO.getTimeImport());
                Timestamp timeImportTimestamp = new Timestamp(parsedDate.getTime());
                importGoods.setTimeImport(timeImportTimestamp);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        importGoods.setSupplier(importGoodsDTO.getSupplier() != null ? importGoodsDTO.getSupplier() : importGoods.getSupplier());
        importGoods.setPaid(importGoodsDTO.getPaid() != null ? importGoodsDTO.getPaid() : importGoods.getPaid());

        importGoodsRepository.save(importGoods);

        List<ImportGoodsDetail> list = new ArrayList<>();
        List<ImportGoodsDetail> listOld = importGoodsDetailRepository.findImportGoodsDetailByImportGoods_ImportGoodsId(importGoods.getImportGoodsId());


        for (ImportGoodsDetailDTO importGoodsDetailDTO : listDetailDTO) {
            ImportGoodsDetail importGoodsDetail = new ImportGoodsDetail();

            ImportGoods ig = findImportGoods(importGoods.getImportGoodsId());
            importGoodsDetail.setImportGoods(ig);
            Goods goods = (importGoodsDetailDTO.getGoodsId() != null) ? findGoods(importGoodsDetailDTO.getGoodsId()) : importGoodsDetail.getGoods();
            importGoodsDetail.setGoods(goods);
            GoodsUnit goodsUnit = goodsUnitRepository.findById(importGoodsDetailDTO.getGoodsUnitId()).orElseThrow(() -> new IllegalStateException("GoodsUnit not found"));
//            GoodsUnit goodsUnit = goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(), true);
            importGoodsDetail.setGoodsUnit(goodsUnit);
            importGoodsDetail.setAmount(importGoodsDetailDTO.getAmount() != null ? importGoodsDetailDTO.getAmount() : importGoodsDetail.getAmount());
            importGoodsDetail.setCost(goodsUnit.getCost());
            importGoodsDetail.setTotal(importGoodsDetail.getCost() * importGoodsDetail.getAmount());

            list.add(importGoodsDetail);
            if (importGoods.getStatus().equals(Status.IMPORT)) {
                if (importGoodsDTO.getImportGoodsId() != null) {
                    for (ImportGoodsDetail detail : listOld) {
                        goods.setInventory(goods.getInventory() - detail.getAmount() * importGoodsDetailDTO.getAmountUnit());
                    }
                }
                goods.setInventory(goods.getInventory() + importGoodsDetail.getAmount() * importGoodsDetailDTO.getAmountUnit());
                goodsRepository.save(goods);
            }
        }
        importGoodsDetailRepository.deleteImportGoodsDetailByImportGoodsId(importGoods.getImportGoodsId());

        if (!list.isEmpty()) {
            importGoodsDetailRepository.saveAll(list);
        } else {
            throw new EmptyListException("Chi tiết phiếu nhập không được để trống");
        }

        Float sumTotal = importGoodsDetailRepository.getSumOfImportGoodsDetails(importGoods);
        importGoods.setPrice(sumTotal);

        importGoodsRepository.save(importGoods);

        if (importGoods.getStatus().equals(Status.IMPORT)) {
            Long userId = UserUtils.getUser().getStaffId();
            Staff staff = findStaff(userId);

            overviewService.writeRecentActivity(staff.getUsername(), "tạo phiếu nhập hàng", importGoods.getPrice(), new Timestamp(System.currentTimeMillis()));
            FundBook fundBook = new FundBook();
            fundBook.setFundBookId("TT" + importGoods.getImportGoodsId());
            fundBook.setTime(new Timestamp(System.currentTimeMillis()));
            fundBook.setType(Status.EXPENSE);
            fundBook.setPaidMethod(null);
            fundBook.setValue(importGoods.getPrice());
            fundBook.setPayerReceiver(importGoods.getSupplier());
            fundBook.setStaff(staff.getStaffName());
            fundBook.setNote("Chi tiền trả nhà cung cấp");
            fundBook.setStatus(Status.COMPLETE);
            fundBookRepository.save(fundBook);
        }
    }

    public ResponseDTO cancelImportGoods(String id) {
        try {
            log.info("----- Start cancel import goods -----");
            ImportGoods importGoods = findImportGoods(id);
            List<ImportGoodsDetail> listOld = importGoodsDetailRepository.findImportGoodsDetailByImportGoods_ImportGoodsId(importGoods.getImportGoodsId());
            if (importGoods.getStatus().equals(Status.IMPORT)) {
                for (ImportGoodsDetail detail : listOld) {
                    Goods goods =  findGoods(detail.getGoods().getGoodsId());
                    GoodsUnit goodsUnit = goodsUnitRepository.findById(detail.getGoodsUnit().getGoodsUnitId()).orElseThrow(() -> new IllegalStateException("GoodsUnit not found"));
                    GoodsUnit goodsUnitDefault = goodsUnitRepository.findGoodsUnitByGoodsIdAndIsDefault(goods.getGoodsId(),true);
                    Long amountUnit = (long) Math.round(goodsUnit.getCost()/goodsUnitDefault.getCost());
                    goods.setInventory(goods.getInventory() - detail.getAmount()*amountUnit);
                    goodsRepository.save(goods);
                }
            }
            importGoods.setStatus(Status.CANCEL);
            importGoodsRepository.save(importGoods);
            log.info("----- End cancel import goods -----");
            return ResponseUtils.success("Hủy nhập hàng thành công");
        } catch (Exception e) {
            log.info("----- Cancel import goods failed -----\n" + e.getMessage());
            return ResponseUtils.error("Hủy nhập hàng thất bại");
        }
    }

    private ImportGoods findImportGoods(String id) {
        return importGoodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import Goods not found with id " + id));
    }

    private Goods findGoods(String id) {
        return goodsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private Staff findStaff(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id " + id));
    }

    public ResponseDTO findListImportGoodsDetailByImportGoodsId(String id) {
        log.info("------- Get Import Goods Detail Start -------");
        Map<String, Object> importInfo = new HashMap<>();
        try {
            List<ImportGoodsDetail> importGoodsDetails = importGoodsDetailRepository.findImportGoodsDetailByImportGoods(findImportGoods(id));
            List<Object> details = new ArrayList<>();
            importInfo.put("ImportGoods", importGoodsDetails.get(0).getImportGoods());

            for (ImportGoodsDetail importDetail : importGoodsDetails) {
                importDetail.setImportGoods(null);
                importDetail.setGoods(null);
                details.add(importDetail);
            }
            importInfo.put("ListDetail", details);
            log.info("------- Get Import Goods Detail End -------");
            return ResponseUtils.success(importInfo, "Hiển thị chi tiết nhập hàng thành công");
        } catch (Exception e) {
            log.error("getById_isFail" + e.getMessage());
            return ResponseUtils.error(importInfo, "Hiển thị chi tiết nhập hàng thất bại");

        }
    }

}
