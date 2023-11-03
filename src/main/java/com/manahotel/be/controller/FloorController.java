package com.manahotel.be.controller;

import com.manahotel.be.model.dto.FloorDTO;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.service.FloorService;
import com.manahotel.be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Floor")
public class FloorController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private FloorService floorService;

    @GetMapping("/list-by-floor")
    public ResponseDTO getAllRoomByFloor() {
        return floorService.getAllRoomByFloor();
    }

    @GetMapping
    public List<Floor> getAllFloor() {
        return roomService.getAllFloor();
    }

    @PostMapping
    public ResponseEntity<String> createFloor(FloorDTO floorDTO) {
        return roomService.createFloor(floorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFloor(@PathVariable int id, FloorDTO floorDTO) {
        return roomService.updateFloor(id, floorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFloorById(@PathVariable int id) {
        return roomService.deleteFloorById(id);
    }

    @GetMapping("/{id}")
    public Floor getFloorById(@PathVariable int id) {
        return roomService.getFloorById((long) id);
    }
}
