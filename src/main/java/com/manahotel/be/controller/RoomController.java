package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.RoomDTO;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return roomService.createRoom(roomDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoom(@PathVariable String id, RoomDTO roomDTO) {
        return roomService.updateRoom(id, roomDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRoomById(@PathVariable List<String> id) {
        return roomService.deleteRoomByList(id);
    }
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable String id) {
        return roomService.getRoomById(id);
    }
}
