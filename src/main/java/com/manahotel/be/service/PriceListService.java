package com.manahotel.be.service;

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
    public List<PriceList> getAllPriceList(){
        return priceListRepository.findAll();
    }

    public Map<String, Object> getPriceListByIdWithPriceListDetailList(String id) {
        Map<String, Object> priceListInfo = new HashMap<>();
        Map<String, Object> priceListDetailWithDayOfWeek = new HashMap<>();

        try {
            PriceList priceList = getPriceListById(id);
            List<PriceListDetail> priceListDetailList = priceListDetailRepository.findPriceListDetailByPriceList(priceList);

            for (PriceListDetail priceListDetail : priceListDetailList) {
                List<String> dayOfWeekList = Arrays.stream(priceListDetail.getDayOfWeek().split("\\|")).collect(Collectors.toList());
                priceListDetailWithDayOfWeek.put("PriceListDetail", priceListDetail);
                priceListDetailWithDayOfWeek.put("DayOfWeekList", dayOfWeekList);
            }

            priceListInfo.put("PriceList", priceList);
            priceListInfo.put("priceListDetailList", priceListDetailWithDayOfWeek.entrySet().toArray());

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




    private void commonMapping(PriceList priceList, PriceListDTO dto) throws IOException {
        priceList.setPriceListId(dto.getPriceListName() != null ? dto.getPriceListName() : priceList.getPriceListName());
        priceList.setEffectiveTimeStart(dto.getEffectiveTimeStart() != null ? dto.getEffectiveTimeStart() : priceList.getEffectiveTimeStart());
        priceList.setEffectiveTimeEnd(dto.getEffectiveTimeEnd() != null ? dto.getEffectiveTimeEnd() : priceList.getEffectiveTimeEnd());
        priceList.setStatus(dto.getStatus() != null ? dto.getStatus() : priceList.getStatus());
        priceList.setNote(dto.getNote() != null ? dto.getNote() : priceList.getNote());
    }

    private void commonMapping(PriceListDetail priceListDetail, PriceListDetailDTO dto) throws IOException {
        priceListDetail.setPriceByDay(dto.getPriceByDay() != null ? dto.getPriceByDay() : priceListDetail.getPriceByDay());
        priceListDetail.setPriceByNight(dto.getPriceByNight() != null ? dto.getPriceByNight() : priceListDetail.getPriceByNight());
        priceListDetail.setPriceByHour(dto.getPriceByHour() != null ? dto.getPriceByHour() : priceListDetail.getPriceByHour());
        priceListDetail.setTimeApply(dto.getTimeApply() != null ? dto.getTimeApply() : priceListDetail.getTimeApply());
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
