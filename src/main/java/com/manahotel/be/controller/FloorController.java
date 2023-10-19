package com.manahotel.be.controller;

import com.manahotel.be.model.dto.FloorDTO;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createFloor(@RequestBody FloorDTO floorDTO) {
        String response = roomService.createFloor(floorDTO);
        if(response.equals("CreateFloorSuccess")){
            return new ResponseEntity<>("Thêm Khu vực thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Thêm Khu vực Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFloor(@PathVariable int id, @RequestBody FloorDTO floorDTO) {
        String response = roomService.updateFloor(id, floorDTO);
        if(response.equals("UpdateFloorSuccess")){
            return new ResponseEntity<>("Cập nhật Khu vực thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Cập nhật Khu vực Thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFloorById(@PathVariable int id) {
        String response = roomService.deleteFloorById(id);
        if (response.equals("DeleteFloorSuccess")) {
            return new ResponseEntity<>("Xóa khu vực thành công", HttpStatus.OK);
        } else if (response.equals("NOT_FOUND")) {
            return new ResponseEntity<>("Không tìm thấy Khu vực", HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>("Xóa khu vực thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Floor getFloorById(@PathVariable int id) {
        return roomService.getFloorById((long) id);
    }
}
