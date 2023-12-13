package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.PriceListDTO;
import com.manahotel.be.model.dto.response.PriceListDetailDTO;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.PriceListDetail;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.PriceListDetailRepository;
import com.manahotel.be.repository.PriceListRepository;
import com.manahotel.be.repository.RoomClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PriceListService {

    @Autowired
    private PriceListRepository priceListRepository;

    @Autowired
    PriceListDetailRepository priceListDetailRepository;
    
    @Autowired
    private RoomClassRepository roomClassRepository;
    public ResponseDTO getAllPriceList(){
        log.info("------- Get All Price List Start -------");
        List<Object> AllPriceList = new ArrayList<>();
        try {
            List<PriceList> listPriceList =  priceListRepository.findAll();
            for(PriceList list : listPriceList) {
                if(!list.getPriceListId().equals("BG000000")){
                    Map<String, Object>  priceListInfo = getPriceListByIdWithPriceListDetailList(list.getPriceListId());
                    AllPriceList.add(priceListInfo);
                }
            }
        } catch (ResourceNotFoundException ef) {
            log.error(ef.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("------- Get All Price List End -------");
            return ResponseUtils.success(AllPriceList, "GetAllPriceListSuccessfully");
    }

    public Map<String, Object> getPriceListByIdWithPriceListDetailList(String id) {
        log.info("------- Get Price List By ID Start -------");

        Map<String, Object> priceListInfo = new HashMap<>();
        List<Map<String, Object>> allRoomClasses = new ArrayList<>();

        try {
            PriceList priceList = getPriceListById(id);
            List<String> listRoomCategoryId = priceListDetailRepository.getDistinctRoomClassByPriceList(priceList);

            for (String roomCategoryId : listRoomCategoryId) {

                RoomCategory roomClass = getRoomCategoryById(roomCategoryId);
                List<PriceListDetail> listPriceListDetailByRoomClass = priceListDetailRepository.getAllPriceListDetailByRoomCategoryId(roomCategoryId, priceList.getPriceListId());

                List<Map<String, Object>> roomClassPriceListDetailList = new ArrayList<>();

                for (PriceListDetail priceListDetail : listPriceListDetailByRoomClass) {
                    List<String> dayOfWeekList = Arrays.stream(priceListDetail.getDayOfWeek().split("\\|")).collect(Collectors.toList());
                    priceListDetail.setRoomCategory(null);
                    priceListDetail.setPriceList(null);
                    Map<String, Object> priceListDetailWithDayOfWeek = new HashMap<>();
                    priceListDetailWithDayOfWeek.put("PriceListDetail", priceListDetail);
                    priceListDetailWithDayOfWeek.put("DayOfWeekList", dayOfWeekList);
                    roomClassPriceListDetailList.add(priceListDetailWithDayOfWeek);
                }
                Map<String, Object> withRoomClass = new HashMap<>();
                withRoomClass.put("RoomClass", roomClass);
                withRoomClass.put("PriceListDetailWithDayOfWeek", roomClassPriceListDetailList);
                allRoomClasses.add(withRoomClass);
            }

            priceListInfo.put("ListPriceListDetail", allRoomClasses);
            priceListInfo.put("PriceList", priceList);
        } catch (ResourceNotFoundException ef) {
            log.error(ef.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("------- Get Price List By ID End -------");
        return priceListInfo;
    }

    public ResponseDTO createPriceList(PriceListDTO priceListDTO, List<PriceListDetailDTO> priceListDetailDTO){
        log.info("------- Create Price List Start -------");
        try{
            PriceList lastestPriceList = priceListRepository.findTopByOrderByPriceListIdDesc();
            String latestId = (lastestPriceList == null) ? null : lastestPriceList.getPriceListId();
            String nextId = IdGenerator.generateId(latestId, "BG");

            PriceList priceList = new PriceList();
            // Add priceList
            priceList.setPriceListId(nextId);
            commonMapping(priceList, priceListDTO);
            priceListRepository.save(priceList);
            log.info("Save Price List Successfully");
            for (PriceListDetailDTO  pldDTO : priceListDetailDTO) {
                PriceListDetail priceListDetail = new PriceListDetail();
                priceListDetail.setPriceList(priceList);

                if(pldDTO.getRoomCategoryId() != null) {
                    RoomCategory roomCategories = getRoomCategoryById(pldDTO.getRoomCategoryId());
                    priceListDetail.setRoomCategory(roomCategories);
                }
                List<String> listDayOfWeek = pldDTO.getDayOfWeek();
                StringBuilder dayOfWeekBuilder = new StringBuilder();
                for (String day : listDayOfWeek) {
                    dayOfWeekBuilder.append(day).append("|");
                    priceListDetail.setDayOfWeek(dayOfWeekBuilder.toString());
                }
                commonMapping(priceListDetail, pldDTO);
                priceListDetailRepository.save(priceListDetail);
                log.info("Save Price List Detail Successfully");
            }
        }catch (Exception e){
            return ResponseUtils.error("Thêm bảng giá mới thất bại");
        }
        log.info("------- Create Price List End -------");
        return ResponseUtils.success("Thêm bảng giá mới thành công");
    }

    public ResponseDTO updatePriceList(String priceListId, PriceListDTO PriceListDTO, List<PriceListDetailDTO> PriceListDetailDTO) {
        log.info("------- Update Price List Start -------");
        try {
            PriceList priceList = getPriceListById(priceListId);
            commonMapping(priceList, PriceListDTO);
            priceListRepository.save(priceList);
            log.info("Save Price List Successfully");

            List<PriceListDetail> priceListDetails = priceListDetailRepository.getAllPriceListDetailByPriceListId(priceListId);
            for(PriceListDetail priceListDetail : priceListDetails){
                priceListDetailRepository.deleteById(priceListDetail.getPriceListDetailId());
            }
            for (PriceListDetailDTO updatedPldDTO : PriceListDetailDTO) {
                PriceListDetail priceListDetail = new PriceListDetail();
                priceListDetail.setPriceList(priceList);

                if (updatedPldDTO.getRoomCategoryId() != null) {
                    RoomCategory roomCategory = getRoomCategoryById(updatedPldDTO.getRoomCategoryId());
                    priceListDetail.setRoomCategory(roomCategory);
                }
                List<String> listDayOfWeek = updatedPldDTO.getDayOfWeek();
                StringBuilder dayOfWeekBuilder = new StringBuilder();
                for (String day : listDayOfWeek) {
                    dayOfWeekBuilder.append(day).append("|");
                }
                priceListDetail.setDayOfWeek(dayOfWeekBuilder.toString());
                commonMapping(priceListDetail, updatedPldDTO);
                priceListDetailRepository.save(priceListDetail);
                log.info("Save Price List Detail Successfully");
            }
        }catch (ResourceNotFoundException ex){
            return ResponseUtils.error("NOT FOUND");
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseUtils.error("Lỗi khi cập nhật bảng giá");
        }
        log.info("------- Update Price List End -------");
        return ResponseUtils.success("Cập nhật bảng giá thành công");
    }

    public ResponseDTO deletePriceListById(String id) {
        try{
                PriceList priceList = getPriceListById(id);
                priceList.setStatus(Status.DELETE);
                priceListRepository.save(priceList);

        }catch (ResourceNotFoundException rnf){
            return ResponseUtils.error("NOT FOUND");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("Delete Price List Fail");
        }
        return ResponseUtils.success(id + "Delete Price List Successfully");
    }

    private void commonMapping(PriceList priceList, PriceListDTO dto) throws IOException {
        priceList.setPriceListName(dto.getPriceListName() != null ? dto.getPriceListName() : priceList.getPriceListName());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (dto.getEffectiveTimeStart() != null && dto.getEffectiveTimeEnd() != null) {
                Date timesStart = dateFormat.parse(dto.getEffectiveTimeStart());
                Timestamp timesStartTimestamp = new Timestamp(timesStart.getTime());
                priceList.setEffectiveTimeStart(timesStartTimestamp);
                Date timesEnd = dateFormat.parse((dto.getEffectiveTimeEnd()));
                Timestamp timesEndTimestamp = new Timestamp(timesEnd.getTime());
                priceList.setEffectiveTimeEnd(timesEndTimestamp);
            }else {
                priceList.setEffectiveTimeStart(priceList.getEffectiveTimeStart());
                priceList.setEffectiveTimeEnd(priceList.getEffectiveTimeEnd());
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        priceList.setStatus(dto.getStatus() != null ? dto.getStatus() : priceList.getStatus());
        priceList.setNote(dto.getNote() != null ? dto.getNote() : priceList.getNote());
    }

    private void commonMapping( PriceListDetail priceListDetail, PriceListDetailDTO dto) throws IOException {
        priceListDetail.setPriceByDay(dto.getPriceByDay() != null ? dto.getPriceByDay() : priceListDetail.getPriceByDay());
        priceListDetail.setPriceByNight(dto.getPriceByNight() != null ? dto.getPriceByNight() : priceListDetail.getPriceByNight());
        priceListDetail.setPriceByHour(dto.getPriceByHour() != null ? dto.getPriceByHour() : priceListDetail.getPriceByHour());
        try {
            if(dto.getTimeApply() !=null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date timesApply = dateFormat.parse(dto.getTimeApply());
                Timestamp timesApplyTimestamp = new Timestamp(timesApply.getTime());
                priceListDetail.setTimeApply(timesApplyTimestamp);
            }else {
                priceListDetail.setTimeApply(priceListDetail.getTimeApply());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }

    public PriceList getPriceListById(String id) {
        return priceListRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Price List not found with id " + id));
    }

}
