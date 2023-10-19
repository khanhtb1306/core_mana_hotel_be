package com.manahotel.be.controller;

import com.manahotel.be.model.dto.RoomDTO;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getAllRoom() {
        return roomService.getAllRoom();
    }

    @PostMapping
    public ResponseEntity<String> createRoom(RoomDTO roomDTO) {
        String response = roomService.createRoom(roomDTO);
        if (response.equals("CreateRoomSuccess")) {
            return new ResponseEntity<>("Thêm phòng thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Thêm phòng Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoom(@PathVariable String id, RoomDTO roomDTO) {
        String response = roomService.updateRoom(id, roomDTO);
        if (response.equals("UpdateRoomSuccess")) {
            return new ResponseEntity<>("Cập nhật phòng thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cập nhật phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomById(@PathVariable String id) {
        String response = roomService.deleteRoomById(id);
        if (response.equals("DeleteRoomSuccess")) {
            return new ResponseEntity<>("Xóa phòng thành công", HttpStatus.OK);
        } else if (response.equals("NOT_FOUND")) {
            return new ResponseEntity<>("Không tìm thấy phòng", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Xóa phòng thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable String id) {
        return roomService.getRoomById(id);
    }
}
