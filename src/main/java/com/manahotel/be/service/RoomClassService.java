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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<String> createRoomClass(RoomCategoryDTO dto) {
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
            return new ResponseEntity<>("Thêm hạng phòng thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't Add Room Class", e.getMessage());
            return new ResponseEntity<>("Thêm hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateRoomClass(String id, RoomCategoryDTO dto) {
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
            return new ResponseEntity<>("Cập nhật hạng phòng thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't Update Room Class", e.getMessage());
            return new ResponseEntity<>("Cập nhật hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteRoomClassById(String id) {
        try {
            RoomCategory roomClass = getRoomCategoryById(id);
            if (roomClass.getStatus() == Status.DELETE) {
                log.info("Room Class Can't delete");
                return new ResponseEntity<>("Phòng đã bị xóa", HttpStatus.NOT_FOUND);
            }
            if (roomClassHasRooms(roomClass)) {
                log.info("Room class has associated rooms");
                return new ResponseEntity<>("Không thể xóa hạng phòng vì có phòng thuộc hạng phòng này", HttpStatus.BAD_REQUEST);
            }
            roomClass.setStatus(Status.DELETE);
            roomClassRepository.save(roomClass);

            log.info("Room class deleted successfully");
            return new ResponseEntity<>("Xóa hạng phòng thành công", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.info("Can't find room class");
            return new ResponseEntity<>("Không tìm thấy hạng phòng", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to delete Room Class", e);
            return new ResponseEntity<>("Xóa hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, String>> deleteRoomClassesByList(List<String> idList) {
        Map<String, String> result = new HashMap<>();

        if (idList == null || idList.isEmpty()) {
            result.put("error", "Danh sách ID không hợp lệ.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        for (String id : idList) {
            ResponseEntity<String> deletionResult = deleteRoomClassById(id);
            result.put(id, deletionResult.getBody());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }

    public Map<String, Object> getRoomClassWithListRoom(String id) {
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
