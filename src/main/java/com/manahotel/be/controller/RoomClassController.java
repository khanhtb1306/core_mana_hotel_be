package com.manahotel.be.controller;

import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.service.RoomClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room-class")
public class RoomClassController {

    @Autowired
    private RoomClassService roomClassService;

    @GetMapping
    public List<Map<String, Object>> getAllRoomClass() {
        return roomClassService.getAllRoomClassWithRoomCount();
    }

    @PostMapping
    public ResponseEntity<String> createRoomClass(RoomCategoryDTO roomCategoryDTO) {
        String createResult =  roomClassService.createRoomClass(roomCategoryDTO);

        if(createResult.equals("CreateRoomClassSuccess")){
            return new ResponseEntity<>("Thêm hạng phòng thành công", HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Thêm hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoomClass(@PathVariable String id, RoomCategoryDTO roomCategoryDTO) {
        String updateResult = roomClassService.updateRoomClass(id, roomCategoryDTO);
        if(updateResult.equals("UpdateRoomClassSuccess")){
            return new ResponseEntity<>("Cập nhật hạng phòng thành công", HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Cập nhật hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomClassById(@PathVariable String id) {
        String deletionResult = roomClassService.deleteRoomClassById(id);
        if (deletionResult.equals("success")) {
            return new ResponseEntity<>("Xóa hạng phòng thành công", HttpStatus.OK);
        } else if (deletionResult.equals("NOT_FOUND")) {
            return new ResponseEntity<>("Không tìm thấy hạng phòng", HttpStatus.NOT_FOUND);
        } else if (deletionResult.equals("BAD_REQUEST")) {
            return new ResponseEntity<>("Không thể xóa hạng phòng vì có phòng thuộc hạng phòng này", HttpStatus.BAD_REQUEST);
        } else  {
            return new ResponseEntity<>("Xóa hạng phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRoomClassById(@PathVariable String id) {
        return roomClassService.getAllRoomClassWithListRoom(id);
    }

}
