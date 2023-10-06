package com.manahotel.be.controller;

import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.service.RoomClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomClass")
public class RoomClassController {

    @Autowired
    private RoomClassService roomClassService;

    @GetMapping
    public List<RoomCategory> getAllRoomClass() {
        return roomClassService.getAllRoomClass();
    }

    @PostMapping
    public RoomCategory createRoomClass(RoomCategoryDTO roomCategoryDTO){

         return roomClassService.createRoomClass(roomCategoryDTO);
    }
    @PutMapping("/{id}")
    public RoomCategory updateRoomClass(@PathVariable String id, RoomCategoryDTO roomCategoryDTO){
        return roomClassService.updateRoomClass(id, roomCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomClassById(@PathVariable String id){
        roomClassService.deleteRoomClassById(id);
    }

    @GetMapping("/{id}")
    public RoomCategory getRoomClassById(@PathVariable String id){
        return roomClassService.getRoomCategoryById(id);
    }

}
