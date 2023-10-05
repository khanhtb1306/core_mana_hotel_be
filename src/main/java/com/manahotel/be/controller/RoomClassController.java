package com.manahotel.be.controller;

import com.manahotel.be.model.entity.Role;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.service.RoomClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
