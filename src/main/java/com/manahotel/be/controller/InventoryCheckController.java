package com.manahotel.be.controller;

import com.manahotel.be.model.entity.InventoryCheckDetail;
import com.manahotel.be.model.dto.request.InventoryCheckRequest;
import com.manahotel.be.model.dto.response.InventoryCheckResponse;
import com.manahotel.be.service.InventoryCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory-check")
public class InventoryCheckController {

    @Autowired
    private InventoryCheckService service;

    @PostMapping
    public ResponseEntity<String> createInventoryCheck(InventoryCheckRequest request) {
        return service.createInventoryCheck(request.getInventoryCheckDTO(), request.getListInventoryCheckDetailDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInventoryCheck(@PathVariable String id, InventoryCheckRequest request) {
        return service.updateInventoryCheck(id, request.getInventoryCheckDTO(), request.getListInventoryCheckDetailDTO());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelCheck(@PathVariable String id) {
        return service.cancelCheck(id);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllCheck() {
        return service.getAllInventoryCheckWithDetails();
    }

    @GetMapping("/summary/{id}")
    public ResponseEntity<InventoryCheckResponse> getInventoryCheckSummary(@PathVariable String id) {
        return service.getInventoryCheckSummary(id);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<List<InventoryCheckDetail>> findListInventoryCheckDetailByInventoryCheckId(@PathVariable String id) {
        return service.findListInventoryCheckDetailByInventoryCheckId(id);
    }
}
