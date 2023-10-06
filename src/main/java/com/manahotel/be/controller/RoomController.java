package com.manahotel.be.controller;

import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.model.dto.RoomDTO;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.service.RoomClassService;
import com.manahotel.be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Room createRoom(RoomDTO roomDTO){
        return roomService.createRoom(roomDTO);
    }
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable String id, RoomDTO roomDTO){
        return roomService.updateRoom(id, roomDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable String id){
        roomService.deleteRoomById(id);
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable String id){
        return roomService.getRoomById(id);
    }
}
