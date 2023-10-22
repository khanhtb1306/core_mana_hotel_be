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
        return roomClassService.createRoomClass(roomCategoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoomClass(@PathVariable String id, RoomCategoryDTO roomCategoryDTO) {
        return roomClassService.updateRoomClass(id, roomCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRoomClassById(@PathVariable List<String> id) {
        return roomClassService.deleteRoomClassesByList(id);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRoomClassById(@PathVariable String id) {
        return roomClassService.getAllRoomClassWithListRoom(id);
    }

}
