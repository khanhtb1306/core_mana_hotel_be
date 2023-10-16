package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.RoomClassRepository;
import com.manahotel.be.repository.RoomRepository;
import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private static final Long ACTIVATE = Status.ACTIVATE.getStatusId();
    private static final Long DEACTIVATE = Status.DEACTIVATE.getStatusId();

    public List<Map<String, Object>> getAllRoomClassWithRoomCount() {
        List<Object[]> roomCategories = roomClassRepository.findRoomCategoriesWithRoomCount();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] roomCategory : roomCategories) {
            RoomCategory rc = (RoomCategory) roomCategory[0];
            Long roomCount = (Long) roomCategory[1];
            List<Room> rooms = roomRepository.findByRoomCategory(rc);

            Map<String, Object> roomInfo = new HashMap<>();
            roomInfo.put("roomCategory", rc);
            roomInfo.put("roomTotal", roomCount);
            roomInfo.put("listRoom", rooms.toArray());

            result.add(roomInfo);
        }
        return result;
    }

    private void commonMapping(RoomCategory roomClass, RoomCategoryDTO dto) {
        roomClass.setRoomCategoryName(dto.getRoomCategoryName());
        roomClass.setPriceByDay(dto.getPriceByDay());
        roomClass.setPriceByNight(dto.getPriceByNight());
        roomClass.setPriceByHour(dto.getPriceByHour());
        roomClass.setRoomCapacity(dto.getRoomCapacity());
        roomClass.setRoomArea(dto.getRoomArea());
        roomClass.setDescription(dto.getDescription());
    }

    public String createRoomClass(RoomCategoryDTO dto) {
        try {
            log.info("------- Add Room Class Start -------");
            RoomCategory latestRoomCategory = roomClassRepository.findTopByOrderByRoomCategoryIdDesc();
            String latestId = (latestRoomCategory == null) ? null : latestRoomCategory.getRoomCategoryId();
            String nextId = IdGenerator.generateId(latestId, "HP");

            RoomCategory roomClass = new RoomCategory();
            roomClass.setRoomCategoryId(nextId);
            roomClass.setStatus(ACTIVATE);

            commonMapping(roomClass, dto);

            roomClass.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            roomClass.setCreatedById(dto.getCreatedById());
            roomClassRepository.save(roomClass);
            log.info("------- Add Room Class End -------");
            return "Tạo hạng phòng thành công";
        } catch (Exception e) {
            log.info("Can't Add Room Class", e.getMessage());
            return "Tạo hạng phòng thất bại";
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
            roomClass.setUpdatedById(dto.getCreatedById());

            roomClassRepository.save(roomClass);

            log.info("------- Update Room Class End -------");
            return "Cập nhật hạng phòng thành công";
        } catch (Exception e) {
            log.info("Can't Update Room Class", e.getMessage());
            return "Cập nhật hạng phòng thất bại";
        }
    }

    public String deleteRoomClassById(String id) {
        try {
            RoomCategory roomClass = getRoomCategoryById(id);
            if (roomClass == null) {
                log.info("Can't find room class id");
                return null;
            }

            roomClass.setStatus(DEACTIVATE);
            roomClassRepository.save(roomClass);

            return "Xóa hạng phòng thành công";
        } catch (Exception e) {
            log.info("Can't Delete Room Class", e.getMessage());
            return "Xóa hạng phòng thất bại";
        }
    }

    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }
}
