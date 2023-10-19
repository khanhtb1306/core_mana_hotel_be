package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.RoomClassRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoomClassService {

    @Autowired
    private RoomClassRepository roomClassRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Map<String, Object>> getAllRoomClassWithRoomCount() {
        List<Object[]> roomCategories = roomClassRepository.findRoomCategoriesWithRoomCount();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] roomCategory : roomCategories) {
            RoomCategory rc = (RoomCategory) roomCategory[0];
            Long roomCount = (Long) roomCategory[1];
            Map<String, Object> roomInfo = new HashMap<>();
            roomInfo.put("roomCategory", rc);
            roomInfo.put("roomTotal", roomCount);

            result.add(roomInfo);
        }
        return result;
    }

    private void commonMapping(RoomCategory roomClass, RoomCategoryDTO dto) throws IOException {
        roomClass.setRoomCategoryName(dto.getRoomCategoryName());
        roomClass.setPriceByDay(dto.getPriceByDay());
        roomClass.setPriceByNight(dto.getPriceByNight());
        roomClass.setPriceByHour(dto.getPriceByHour());
        roomClass.setNumOfAdults(dto.getNumOfAdults());
        roomClass.setNumOfChildren(dto.getNumOfChildren());
        roomClass.setRoomArea(dto.getRoomArea());
        roomClass.setDescription(dto.getDescription());
        roomClass.setImage(dto.getImage() != null ? dto.getImage().getBytes() : null);
    }

    public String createRoomClass(RoomCategoryDTO dto) {
        try {
            log.info("------- Add Room Class Start -------");
            RoomCategory latestRoomCategory = roomClassRepository.findTopByOrderByRoomCategoryIdDesc();
            String latestId = (latestRoomCategory == null) ? null : latestRoomCategory.getRoomCategoryId();
            String nextId = IdGenerator.generateId(latestId, "HP");

            RoomCategory roomClass = new RoomCategory();
            roomClass.setRoomCategoryId(nextId);
            roomClass.setStatus(Status.ACTIVATE);

            commonMapping(roomClass, dto);

            roomClass.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            roomClassRepository.save(roomClass);
            log.info("------- Add Room Class End -------");
            return "CreateRoomClassSuccess";
        } catch (Exception e) {
            log.info("Can't Add Room Class", e.getMessage());
            return "CreateRoomClassFail";
        }
    }

    public String updateRoomClass(String id, RoomCategoryDTO dto) {
        log.info("------- Update Room Class Start -------");
        try {

            RoomCategory roomClass = getRoomCategoryById(id);
            if (roomClass == null) {
                return null;
            }

            commonMapping(roomClass, dto);

            roomClass.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

            roomClassRepository.save(roomClass);

            log.info("------- Update Room Class End -------");
            return "UpdateRoomClassSuccess";
        } catch (Exception e) {
            log.info("Can't Update Room Class", e.getMessage());
            return "UpdateRoomClassFail";
        }
    }

    public String deleteRoomClassById(String id) {
        try {
            RoomCategory roomClass = getRoomCategoryById(id);
            if (roomClass == null) {
                log.info("Can't find room class id");
                return "NOT_FOUND";
            }
            if (roomClassHasRooms(roomClass)) {
                log.info("Room class has associated rooms");
                return "BAD_REQUEST";
            }
            roomClass.setStatus(Status.DELETE);
            roomClassRepository.save(roomClass);

            log.info("Room class deleted successfully");
            return "success";
        } catch (Exception e) {
            log.error("Failed to delete Room Class", e);
            return "DeleteFail";
        }
    }

    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }

    public Map<String, Object> getAllRoomClassWithListRoom(String id) {
        RoomCategory roomCategory = getRoomCategoryById(id);
        Map<String, Object> roomInfo = new HashMap<>();

        if (roomCategory != null) {
            List<Room> rooms = roomRepository.findByRoomCategory(roomCategory);
            roomInfo.put("roomCategory", roomCategory);
            roomInfo.put("listRoom", rooms);
        }

        return roomInfo;
    }

    boolean roomClassHasRooms(RoomCategory roomClass) {
        List<Room> rooms = roomRepository.findByRoomCategory(roomClass);
        if (!rooms.isEmpty()) {
            return true;
        }
        return false;
    }
}
