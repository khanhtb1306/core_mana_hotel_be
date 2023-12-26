package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.IdGenerator;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.dto.response.RoomCategoryDTO;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.ReservationDetailCustomer;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
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

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private ReservationDetailCustomerRepository reservationDetailCustomerRepository;

    public ResponseDTO getAllRoomByRoomClass() {
        List<Object[]> listRoomClass = roomClassRepository.findRoomByRoomCategory();
        List<Map<String, Object>> result = new ArrayList<>();

        for(Object[] roomClass : listRoomClass) {
            RoomCategory roomCategory = (RoomCategory) roomClass[0];
            List<Room> listRooms = roomRepository.findByRoomCategory(roomCategory);
            List<Map<String, Object>> roomListWithRDs = new ArrayList<>();
            for(Room room : listRooms) {
                List<ReservationDetail> listReservationDetails = reservationDetailRepository.findReservationDetailByRoomAndReservationDetailStatus(room, Status.ACTIVATE);

                List<Map<String, Object>> RDListWithRDCs = new ArrayList<>();
                for(ReservationDetail reservationDetail : listReservationDetails) {
                    List<ReservationDetailCustomer> listReservationDetailCustomers = reservationDetailCustomerRepository.findReservationDetailCustomerByReservationDetail(reservationDetail);

                    Map<String, Object> reservationDetailCustomerInfo = new HashMap<>();
                    reservationDetailCustomerInfo.put("reservationDetail", reservationDetail);
                    reservationDetailCustomerInfo.put("listReservationDetailCustomers", listReservationDetailCustomers.toArray());
                    RDListWithRDCs.add(reservationDetailCustomerInfo);
                }

                Map<String, Object> reservationDetailInfo = new HashMap<>();
                reservationDetailInfo.put("room", room);
                reservationDetailInfo.put("RDListWithRDCs", RDListWithRDCs.toArray());
                roomListWithRDs.add(reservationDetailInfo);
            }

            Map<String, Object> roomInfo = new HashMap<>();
            roomInfo.put("roomCategory", roomCategory);
            roomInfo.put("roomListWithRDs", roomListWithRDs.toArray());
            result.add(roomInfo);
        }

        return ResponseUtils.success(result, "Hiển thị danh sách phòng theo danh sách hạng phòng thành công");
    }

    public ResponseDTO findActiveRoomCategoriesWithActiveRooms() {
        List<RoomCategory> roomCategories = roomClassRepository.findActiveRoomCategoriesWithActiveRooms();
        List<Object> result = new ArrayList<>();

        for (RoomCategory roomCategory : roomCategories) {
            List<Room> rooms = roomRepository.findByRoomCategoryAndStatus(roomCategory, Status.ACTIVATE);
            Map<String, Object> roomInfo = new HashMap<>();
            roomInfo.put("roomCategory", roomCategory);
            roomInfo.put("ListRoom", rooms.toArray());
            result.add(roomInfo);
        }
        return ResponseUtils.success(result, "is_successfully");
    }


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
            roomInfo.put("ListRoom", rooms.toArray());

            result.add(roomInfo);
        }
        return result;
    }

    private void commonMapping(RoomCategory roomClass, RoomCategoryDTO dto) throws IOException {
        roomClass.setRoomCategoryName(dto.getRoomCategoryName() != null ? dto.getRoomCategoryName() : roomClass.getRoomCategoryName());
        roomClass.setPriceByDay(dto.getPriceByDay() != null ? dto.getPriceByDay() : roomClass.getPriceByDay());
        roomClass.setPriceByNight(dto.getPriceByNight() != null ? dto.getPriceByNight() : roomClass.getPriceByNight());
        roomClass.setPriceByHour(dto.getPriceByHour() != null ? dto.getPriceByHour() : roomClass.getPriceByHour());
        roomClass.setNumOfAdults(dto.getNumOfAdults() != null ? dto.getNumOfAdults() : roomClass.getNumOfAdults());
        roomClass.setNumOfChildren(dto.getNumOfChildren() != null ? dto.getNumOfChildren() : roomClass.getNumOfChildren());
        roomClass.setNumMaxOfAdults(dto.getNumMaxOfAdults() != null ? dto.getNumMaxOfAdults() : roomClass.getNumMaxOfAdults());
        roomClass.setNumMaxOfChildren(dto.getNumMaxOfChildren() != null ? dto.getNumMaxOfChildren() : roomClass.getNumMaxOfChildren());
        roomClass.setRoomArea(dto.getRoomArea() != null ? dto.getRoomArea() : roomClass.getRoomArea());
        roomClass.setDescription(dto.getDescription() != null ? dto.getDescription() : roomClass.getDescription());
        roomClass.setImage(!dto.getImage().isEmpty() || dto.getImage() != null ? dto.getImage().getBytes() : roomClass.getImage());
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
            roomClass.setCreatedDate(new Timestamp(System.currentTimeMillis()));

            commonMapping(roomClass, dto);

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
            roomClass.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

            commonMapping(roomClass, dto);
            if(!(dto.getStatus() != null ? dto.getStatus() : roomClass.getStatus()).equals(roomClass.getStatus())) {
                List<Room> rooms = roomRepository.findByRoomCategory(roomClass);
                int count = 0;
                for (Room room : rooms){
                    List<ReservationDetail> reservationDetails = reservationDetailRepository.findDetailsByRoomIdAndStatus(room.getRoomId());
                    if(!reservationDetails.isEmpty()){
                        count ++;
                    }
                }
                if(count > 0){
                    return new ResponseEntity<>("Phòng đang được đặt hoặc đang được sử dụng không thể Cập nhật", HttpStatus.BAD_REQUEST);
                }
                for (Room r: rooms){
                    r.setStatus(dto.getStatus());
                }
                roomClass.setStatus(dto.getStatus() != null ? dto.getStatus() : roomClass.getStatus());
            }

            roomClassRepository.save(roomClass);

            log.info("------- Update Room Class End -------");
            return new ResponseEntity<>("Cập nhật hạng phòng thành công", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            log.info("Can't find room class");
            return new ResponseEntity<>("Không tìm thấy hạng phòng", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Can't Update Room Class", e.getMessage());
            return new ResponseEntity<>("Cập nhật hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteRoomClassById(String id) {
        log.info("------- Delete Room Class Start -------");
        try {
            RoomCategory roomClass = getRoomCategoryById(id);
            if (roomClass.getStatus() == Status.DELETE) {
                log.info("Room Class Can't delete");
                return new ResponseEntity<>("Hạng phòng đã bị xóa", HttpStatus.NOT_FOUND);
            }
            if (roomClassHasRooms(roomClass)) {
                log.info("Room class has associated rooms");
                return new ResponseEntity<>("Không thể xóa hạng phòng vì có phòng thuộc hạng phòng này", HttpStatus.BAD_REQUEST);
            }
            roomClass.setStatus(Status.DELETE);
            roomClassRepository.save(roomClass);
            log.info("Room class deleted successfully");
            log.info("------- Delete Room Class end -------");
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
        log.info("------- Delete List Room Class Start -------");

        Map<String, String> result = new HashMap<>();

        if (idList == null || idList.isEmpty()) {
            result.put("error", "Danh sách ID không hợp lệ.");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        for (String id : idList) {
            ResponseEntity<String> deletionResult = deleteRoomClassById(id);
            result.put(id, deletionResult.getBody());
        }
        log.info("------- Delete List Room Class End -------");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }

    public Map<String, Object> getRoomClassWithListRoom(String id) {
        log.info("------- GET Room Class With List Room Start -------");
        Map<String, Object> roomInfo = new HashMap<>();
        try {
            RoomCategory roomCategory = getRoomCategoryById(id);
            List<Room> rooms = roomRepository.findByRoomCategory(roomCategory);
            roomInfo.put("roomCategory", roomCategory);
            roomInfo.put("listRoom", rooms);
        }catch (ResourceNotFoundException e) {
            log.info(e.getMessage());
        }
        log.info("------- GET Room Class With List Room End -------");
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
