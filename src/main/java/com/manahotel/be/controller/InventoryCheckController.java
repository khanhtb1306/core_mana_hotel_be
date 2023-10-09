package com.manahotel.be.controller;

import com.manahotel.be.model.entity.InventoryCheck;
import com.manahotel.be.model.entity.InventoryCheckDetail;
import com.manahotel.be.service.InventoryCheckRequest;
import com.manahotel.be.service.InventoryCheckResponse;
import com.manahotel.be.service.InventoryCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-check")
public class InventoryCheckController {

    @Autowired
    private InventoryCheckService service;

    @PostMapping
    public String createInventoryCheck(@RequestBody InventoryCheckRequest request) {
        return service.createInventoryCheck(request.getInventoryCheckDTO(), request.getListInventoryCheckDetailDTO());
    }

    @PutMapping("/{id}")
    public String updateInventoryCheck(@PathVariable String id, @RequestBody InventoryCheckRequest request) {
        return service.updateInventoryCheck(id, request.getInventoryCheckDTO(), request.getListInventoryCheckDetailDTO());
    }

    @DeleteMapping("/{id}")
    public String cancelCheck(@PathVariable String id) {
        return service.cancelCheck(id);
    }

    @GetMapping
    public List<InventoryCheck> getAllCheck() {
        return service.getAllCheck();
    }

    @GetMapping("/summary/{id}")
    public InventoryCheckResponse getInventoryCheckSummary(@PathVariable String id) {
        return service.getInventoryCheckSummary(id);
    }

    @GetMapping("/details/{id}")
    public List<InventoryCheckDetail> findListInventoryCheckDetailByInventoryCheckId(@PathVariable String id) {
        return service.findListInventoryCheckDetailByInventoryCheckId(id);
    }
}
