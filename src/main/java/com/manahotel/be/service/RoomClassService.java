package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.RoomClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Slf4j
@Service
public class RoomClassService {
    @Autowired
    private RoomClassRepository roomClassRepository;
    private static final Long ACTIVATE = Status.ACTIVATE.getStatusId();
    private static final Long DEACTIVATE = Status.DEACTIVATE.getStatusId();

    public List<RoomCategory>getAllRoomClass(){
        return roomClassRepository.findAll();
    }


    public RoomCategory createRoomClass(RoomCategoryDTO dto){
        try{
                log.info("------- Add Room Class Start -------");
                RoomCategory latestRoomCategory = roomClassRepository.findTopByOrderByRoomCategoryIdDesc();
                String latestId = (latestRoomCategory == null) ? null : latestRoomCategory.getRoomCategoryId();
                String nextId = IdGenerator.generateId(latestId, "HP");

                RoomCategory roomClass = new RoomCategory();
                roomClass.setRoomCategoryId(nextId);
                roomClass.setRoomCategoryName(dto.getRoomCategoryName());
                roomClass.setPriceByDay(dto.getPriceByDay());
                roomClass.setPriceByNight(dto.getPriceByNight());
                roomClass.setPriceByHour(dto.getPriceByHour());
                roomClass.setRoomCapacity(dto.getRoomCapacity());
                roomClass.setRoomArea(dto.getRoomArea());
                roomClass.setStatus(ACTIVATE);
                roomClass.setDescription(dto.getDescription());
                roomClass.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                roomClass.setCreatedById(dto.getCreatedById());
                roomClassRepository.save(roomClass);
                log.info("------- Add Room Class End -------");
                return roomClass;
        }catch (Exception e){
            log.info("Can't Add Room Class", e.getMessage());
            return null;
        }
    }

    public RoomCategory updateRoomClass(String id,RoomCategoryDTO dto){
        log.info("------- Update Room Class Start -------");
        try{

                RoomCategory roomClass = getRoomCategoryById(id);
                if(roomClass == null){ return null;}

                roomClass.setRoomCategoryName(dto.getRoomCategoryName());
                roomClass.setPriceByDay(dto.getPriceByDay());
                roomClass.setPriceByNight(dto.getPriceByNight());
                roomClass.setPriceByHour(dto.getPriceByHour());
                roomClass.setRoomCapacity(dto.getRoomCapacity());
                roomClass.setRoomArea(dto.getRoomArea());
                roomClass.setDescription(dto.getDescription());
                roomClass.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
                roomClass.setUpdatedById(dto.getCreatedById());

                roomClassRepository.save(roomClass);

                log.info("------- Update Room Class End -------");
                return roomClass;
        }catch (Exception e){
            log.info("Can't Update Room Class", e.getMessage());
            return null;
        }
        }

        public void deleteRoomClassById(String id) {
            try {
                RoomCategory roomClass = getRoomCategoryById(id);
                if (roomClass == null) {
                    log.info("Can't find room class id");
                    return;
                }

                roomClassRepository.delete(roomClass);
            } catch (Exception e){
                log.info("Can't Delete Room Class", e.getMessage());
            }
        }

        public RoomCategory getRoomCategoryById(String id){
            return  roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
        }
}
