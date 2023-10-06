package com.manahotel.be.controller;

import com.manahotel.be.model.dto.FloorDTO;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Floor")
public class FloorController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Floor> getAllFloor() {
        return roomService.getAllFloor();
    }

    @PostMapping
    public Floor createFloor(FloorDTO floorDTO){
        return roomService.createFloor(floorDTO);
    }
    @PutMapping("/{id}")
    public Floor updateFloor(@PathVariable int id, FloorDTO floorDTO){
        return roomService.updateFloor(id, floorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFloorById(@PathVariable int id){
        roomService.deleteFloorById(id);
    }

    @GetMapping("/{id}")
    public Floor getFloorById(@PathVariable int id){
        return roomService.getFloorById((long) id);
    }
}
