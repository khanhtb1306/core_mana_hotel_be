package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.FloorDTO;
import com.manahotel.be.model.dto.response.RoomDTO;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.FloorRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.RoomClassRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomClassRepository roomClassRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    //Get All List Room
    public List<Room> getAllRoom() {
        return roomRepository.findByStatusNot(Status.DELETE);
    }

    private void commonMapping(Room room, RoomDTO dto) throws IOException {
        room.setRoomName(dto.getRoomName() != null ? dto.getRoomName() : room.getRoomName());
        room.setNote(dto.getNote() != null ? dto.getNote() : room.getNote());
        room.setImage(dto.getImage() != null ? dto.getImage().getBytes() : null);
        room.setStatus(dto.getStatus() != null ? dto.getStatus(): room.getStatus());
        room.setConditionStatus(dto.getConditionStatus() != null ? dto.getConditionStatus() : room.getConditionStatus());
    }

    public ResponseEntity<String> createRoom(RoomDTO dto) {
        try {
            log.info("------- Add Room Start -------");
            Room latestRoom = roomRepository.findTopByOrderByRoomIdDesc();
            String latestId = (latestRoom == null) ? null : latestRoom.getRoomId();
            String nextId = IdGenerator.generateId(latestId, "P");
            Room room = new Room();
            room.setRoomId(nextId);

            commonMapping(room, dto);

            RoomCategory roomCategory = getRoomCategoryById(dto.getRoomCategoryId());
            room.setRoomCategory(roomCategory);

            Floor floor = getFloorById(dto.getFloorId());
            room.setFloor(floor);

            room.setStatus(Status.ACTIVATE);
            room.setBookingStatus(Status.ROOM_EMPTY);
            room.setConditionStatus(Status.CLEAN);
            room.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            roomRepository.save(room);
            log.info("------- Add Room End -------");
            return new ResponseEntity<>("Thêm phòng thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't Add Room", e.getMessage());
            return new ResponseEntity<>("Thêm phòng Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> updateRoom(String id, RoomDTO dto) {
        try {
            log.info("------- Update Room Start -------");
            Room room = getRoomById(id);
            commonMapping(room, dto);
            if (dto.getRoomCategoryId() != null) {
                RoomCategory roomCategory = getRoomCategoryById(dto.getRoomCategoryId());
                room.setRoomCategory(roomCategory);
            }
            if (dto.getFloorId() != null){
                Floor floor = getFloorById(dto.getFloorId());
                room.setFloor(floor);
            }
            room.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            roomRepository.save(room);
            log.info("------- Update Room End -------");
            return new ResponseEntity<>("Cập nhật phòng thành công", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.info("Can't find room");
            return new ResponseEntity<>("Không tìm thấy phòng", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Can't Update Room", e.getMessage());
            return new ResponseEntity<>("Cập phòng Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> deleteRoomById(String id) {
        log.info("------- DELETE Room By ID Start -------");
        try {
            Room room = getRoomById(id);
            if (room.getStatus() == Status.DELETE) {
                log.info("Phòng đã bị xóa");
                return new ResponseEntity<>("Phòng đã bị xóa", HttpStatus.NOT_FOUND);
            }
            List<ReservationDetail> reservationDetail = reservationDetailRepository.findBookingAndCheckInDetailsByRoomId(id);
            if(reservationDetail != null){
                log.info("Phòng đang được đặt hoặc đang được sử dụng không thể xóa");
                return new ResponseEntity<>("Phòng đang được đặt hoặc đang được sử dụng không thể xóa", HttpStatus.OK);
            }
            room.setStatus(Status.DELETE);
            roomRepository.save(room);
            log.info("Room deleted successfully");

            log.info("------- DELETE Room By ID End -------");
            return new ResponseEntity<>("Xóa phòng thành công", HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            log.info("Can't find room class");
            return new ResponseEntity<>("Không tìm thấy phòng", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Failed to delete Room", e);
            return new ResponseEntity<>("Xóa phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Map<String, String>> deleteRoomByList(List<String> idList) {
        log.info("------- DELETE List Room Start -------");
        Map<String, String> result = new HashMap<>();

        if (idList == null || idList.isEmpty()) {
            result.put("error", "Danh sách ID không hợp lệ.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        for (String id : idList) {
            ResponseEntity<String> deletionResult = deleteRoomById(id);
            result.put(id, deletionResult.getBody());
        }
        log.info("------- DELETE List Room End -------");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public Room getRoomById(String id) {
        return roomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room not found with id" + id));
    }

    public Floor getFloorById(Long id) {
        return floorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor not found with id " + id));
    }

    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }


    public List<Floor> getAllFloor() {
        return floorRepository.findByStatusNot(Status.DELETE);
    }

    public ResponseEntity<String> createFloor(FloorDTO dto) {
        try {
            log.info("------- Add Floor Start -------");

            Floor floor = new Floor();
            floor.setFloorName(dto.getFloorName());
            floor.setStatus(dto.getStatus());
            floor.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            floorRepository.save(floor);
            log.info("------- Add Floor End -------");
            return new ResponseEntity<>("Thêm Khu vực thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't Add Floor", e.getMessage());
            return new ResponseEntity<>("Thêm Khu vực Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateFloor(int id, FloorDTO dto) {
        try {
            log.info("------- Update Floor Start -------");

            Floor floor = getFloorById((long) id);

            floor.setFloorName(dto.getFloorName() != null ? dto.getFloorName() : floor.getFloorName());
            floor.setStatus(dto.getStatus() != null ? dto.getStatus() : floor.getStatus());
            floor.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            floorRepository.save(floor);
            log.info("------- Update Floor End -------");
            return new ResponseEntity<>("Cập nhật Khu vực thành công", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.info("Can't find room class");
            return new ResponseEntity<>("Không tìm thấy Khu vực", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Can't Update Floor", e.getMessage());
            return new ResponseEntity<>("Cập nhật Khu vực Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteFloorById(int id) {
        log.info("------- Delete Area Start -------");
        try {
            Floor floor = getFloorById((long) id);

            floor.setStatus(Status.DELETE);
            floorRepository.save(floor);
            log.info("------- DELETE Area End -------");
            return new ResponseEntity<>("Xóa khu vực thành công", HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            log.info("Can't find floor id", e.getMessage());
            return new ResponseEntity<>("không tìm thấy khu vực thành công", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Can't Delete Floor", e.getMessage());
            return new ResponseEntity<>("Xóa khu vực thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
