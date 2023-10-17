package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.FloorDTO;
import com.manahotel.be.model.dto.RoomDTO;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.FloorRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomClassService roomClassService;

    //Get All List Room
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    private void commonMapping(Room room, RoomDTO dto) throws IOException {
        room.setRoomName(dto.getRoomName());
        room.setNote(dto.getNote());
        room.setImage(dto.getImage() != null ? dto.getImage().getBytes() : null);
    }

    public String createRoom(RoomDTO dto) {
        try {
            log.info("------- Add Room Start -------");
            Room latestRoom = roomRepository.findTopByOrderByRoomIdDesc();
            String latestId = (latestRoom == null) ? null : latestRoom.getRoomId();
            String nextId = IdGenerator.generateId(latestId, "P");
            Room room = new Room();
            room.setRoomId(nextId);

            commonMapping(room, dto);

            RoomCategory roomCategory = roomClassService.getRoomCategoryById(dto.getRoomCategoryId());
            room.setRoomCategory(roomCategory);

            Floor floor = getFloorById(dto.getFloorId());
            room.setFloor(floor);

            room.setStatus(Status.ACTIVATE);
            room.setBookingStatus(0L);
            room.setConditionStatus(0L);

//            room.setCreatedById(dto.getCreatedById());
            room.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            roomRepository.save(room);
            log.info("------- Add Room End -------");
            return "Tạo phòng thành công";
        } catch (Exception e) {
            log.info("Can't Add Room", e.getMessage());
            return "Tạo phòng thất bại";
        }
    }


    public String updateRoom(String id, RoomDTO dto) {
        try {
            log.info("------- Update Room Start -------");
            Room room = getRoomById(id);
            if (room == null) {
                return null;
            }

            commonMapping(room, dto);

            RoomCategory roomCategory = roomClassService.getRoomCategoryById(dto.getRoomCategoryId());
            room.setRoomCategory(roomCategory);

            Floor floor = getFloorById(dto.getFloorId());
            room.setFloor(floor);

//            room.setUpdatedById(dto.getCreatedById());
            room.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            roomRepository.save(room);
            log.info("------- Update Room End -------");
            return "Cập nhật phòng thành công";
        } catch (Exception e) {
            log.info("Can't Update Room", e.getMessage());
            return "Cập nhật phòng thất bại";
        }
    }


    public String deleteRoomById(String id) {
        try {
            Room room = getRoomById(id);
            if (room == null) {
                log.info("Can't find room id");
                return null;
            }
            room.setStatus(Status.DEACTIVATE);
            roomRepository.save(room);

            return "Xóa phòng thành công";

        } catch (Exception e) {
            log.info("Can't Delete Room", e.getMessage());
            return "Xóa phòng thất bại";
        }
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found with id " + id));
    }

    public Floor getFloorById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor not found with id " + id));
    }


    public List<Floor> getAllFloor() {
        return floorRepository.findAll();
    }

    public String createFloor(FloorDTO dto) {
        try {
            log.info("------- Add Floor Start -------");

            Floor floor = new Floor();
            floor.setFloorName(dto.getFloorName());
            floor.setStatus(dto.getStatus());
            floor.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            floorRepository.save(floor);
            log.info("------- Add Floor End -------");
            return "Tạo tầng thành công";
        } catch (Exception e) {
            log.info("Can't Add Floor", e.getMessage());
            return "Tạo tầng thất bại";
        }
    }

    public String updateFloor(int id, FloorDTO dto) {
        try {
            log.info("------- Update Floor Start -------");

            Floor floor = getFloorById((long) id);
            if (floor == null) {
                return null;
            }
            floor.setFloorName(dto.getFloorName());
            floor.setStatus(dto.getStatus());
            floor.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            floorRepository.save(floor);
            log.info("------- Update Floor End -------");
            return "Cập nhật tầng thành công";
        } catch (Exception e) {
            log.info("Can't Update Floor", e.getMessage());
            return "Cập nhật tầng thất bại";
        }
    }

    public String deleteFloorById(int id) {
        try {
            Floor floor = getFloorById((long) id);
            if (floor == null) {
                log.info("Can't find floor id");
                return null;
            }

            floor.setStatus(Status.DEACTIVATE);
            floorRepository.save(floor);

            return "Xóa khu vực thành công";
        } catch (Exception e) {
            log.info("Can't Delete Floor", e.getMessage());
            return "Xóa Khu vực thất bại";
        }
    }

}
