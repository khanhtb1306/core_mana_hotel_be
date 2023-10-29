package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.PriceListDTO;
import com.manahotel.be.model.dto.PriceListDetailDTO;
import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.PriceListDetail;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.PriceListDetailRepository;
import com.manahotel.be.repository.PriceListRepository;
import com.manahotel.be.repository.RoomClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Object> getAllPriceList(){
        List<Object> AllPriceList = new ArrayList<>();
        Map<String, Object> priceListInfo = new HashMap<>();
        List<Map<String, Object>> allRoomClasses = new ArrayList<>();
        List<Map<String, Object>> roomClassPriceListDetailList = new ArrayList<>();
        Map<String, Object> withRoomClass = new HashMap<>();

        try {
            List<PriceList> listPriceList =  priceListRepository.findAll();
            for(PriceList list : listPriceList) {
                PriceList priceList = getPriceListById(list.getPriceListId());
                List<String> listRoomCategoryId = priceListDetailRepository.getDistinctRoomClassByPriceList(priceList);
                for (String roomCategoryId : listRoomCategoryId) {
                    RoomCategory roomClass = getRoomCategoryById(roomCategoryId);
                    for (PriceListDetail priceListDetail : priceListDetailRepository.getAllPriceListDetailByRoomCategoryId(roomCategoryId)) {
                        List<String> dayOfWeekList = Arrays.stream(priceListDetail.getDayOfWeek().split("\\|")).collect(Collectors.toList());
                        priceListDetail.setRoomCategory(null);
                        priceListDetail.setPriceList(null);
                        Map<String, Object> priceListDetailWithDayOfWeek = new HashMap<>();
                        priceListDetailWithDayOfWeek.put("PriceListDetail", priceListDetail);
                        priceListDetailWithDayOfWeek.put("DayOfWeekList", dayOfWeekList);
                        roomClassPriceListDetailList.add(priceListDetailWithDayOfWeek);
                    }
                    withRoomClass.put("RoomClass", roomClass);
                    withRoomClass.put("PriceListDetail", roomClassPriceListDetailList.toArray());
                    allRoomClasses.add(withRoomClass);
                }
                priceListInfo.put("PriceList", priceList);
                priceListInfo.put("ListPriceListDetail", allRoomClasses.toArray());
            }
            AllPriceList.add(priceListInfo);
        } catch (ResourceNotFoundException ef) {
            log.error(ef.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return AllPriceList;

    }

    public Map<String, Object> getPriceListByIdWithPriceListDetailList(String id) {

        Map<String, Object> priceListInfo = new HashMap<>();
        List<Map<String, Object>> allRoomClasses = new ArrayList<>();
        List<Map<String, Object>> roomClassPriceListDetailList = new ArrayList<>();
        Map<String, Object> withRoomClass = new HashMap<>();

        try {
            PriceList priceList = getPriceListById(id);
            List<String> listRoomCategoryId = priceListDetailRepository.getDistinctRoomClassByPriceList(priceList);
            for (String roomCategoryId : listRoomCategoryId) {
                RoomCategory roomClass = getRoomCategoryById(roomCategoryId);
                for (PriceListDetail priceListDetail : priceListDetailRepository.getAllPriceListDetailByRoomCategoryId(roomCategoryId)) {
                    List<String> dayOfWeekList = Arrays.stream(priceListDetail.getDayOfWeek().split("\\|")).collect(Collectors.toList());
                    priceListDetail.setRoomCategory(null);
                    priceListDetail.setPriceList(null);
                    Map<String, Object> priceListDetailWithDayOfWeek = new HashMap<>();
                    priceListDetailWithDayOfWeek.put("PriceListDetail", priceListDetail);
                    priceListDetailWithDayOfWeek.put("DayOfWeekList", dayOfWeekList);
                    roomClassPriceListDetailList.add(priceListDetailWithDayOfWeek);
                }
                withRoomClass.put("RoomClass", roomClass);
                withRoomClass.put("PriceListDetail", roomClassPriceListDetailList.toArray());
                allRoomClasses.add(withRoomClass);
            }

            priceListInfo.put("PriceList", priceList);
            priceListInfo.put("ListPriceListDetail", allRoomClasses.toArray());
        } catch (ResourceNotFoundException ef) {
            log.error(ef.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return priceListInfo;
    }

    public ResponseEntity<String> createPriceList(PriceListDTO priceListDTO, List<PriceListDetailDTO> priceListDetailDTO){
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
            log.info(e.getMessage());
        }
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    public ResponseEntity<String> updatePriceList(String priceListId, PriceListDTO updatedPriceListDTO, List<PriceListDetailDTO> updatedPriceListDetailDTO) {
        try {
            // Tìm danh sách giá dựa trên priceListId
            PriceList priceList = getPriceListById(priceListId);

            // Cập nhật thông tin của danh sách giá
            commonMapping(priceList, updatedPriceListDTO);
            priceListRepository.save(priceList);
            log.info("Cập nhật danh sách giá thành công");

//            // Xóa tất cả các mục chi tiết danh sách giá cũ
            priceListDetailRepository.deleteByPriceList(priceList);

            // Thêm các mục chi tiết danh sách giá mới
            for (PriceListDetailDTO updatedPldDTO : updatedPriceListDetailDTO) {
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
                log.info("Thêm mục chi tiết danh sách giá thành công");
            }
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>("Danh sách giá không tồn tại", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Lỗi khi cập nhật danh sách giá", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Cập nhật Bảng giá thành công", HttpStatus.OK);
    }

    public ResponseEntity<String> deletePriceListById(String id) {
        try{
            PriceList priceList = getPriceListById(id);
            priceList.setStatus(Status.DELETE);
            priceListRepository.save(priceList);
        }catch (ResourceNotFoundException rnf){
            log.error(rnf.getMessage());
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    private void commonMapping(PriceList priceList, PriceListDTO dto) throws IOException {
        priceList.setPriceListId(dto.getPriceListName() != null ? dto.getPriceListName() : priceList.getPriceListName());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (dto.getEffectiveTimeStart() != null) {
                Date timesStart = dateFormat.parse(dto.getEffectiveTimeStart());
                Timestamp timesStartTimestamp = new Timestamp(timesStart.getTime());
                priceList.setEffectiveTimeStart(timesStartTimestamp);
            } else if (dto.getEffectiveTimeEnd() != null) {
                Date timesEnd = dateFormat.parse((dto.getEffectiveTimeEnd()));
                Timestamp timesEndTimestamp = new Timestamp(timesEnd.getTime());
                priceList.setEffectiveTimeEnd(timesEndTimestamp);
            } else {
                priceList.setEffectiveTimeStart(priceList.getEffectiveTimeStart());
                priceList.setEffectiveTimeEnd(priceList.getEffectiveTimeEnd());
            }
        }catch (ParseException e){
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
