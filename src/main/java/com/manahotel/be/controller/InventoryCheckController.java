package com.manahotel.be.controller;

import com.manahotel.be.service.InventoryCheckRequest;
import com.manahotel.be.service.InventoryCheckResponse;
import com.manahotel.be.service.InventoryCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory-check")
public class InventoryCheckController {

    @Autowired
    private InventoryCheckService service;

    @PostMapping
    public String createInventoryCheck(@RequestBody InventoryCheckRequest request) {
        return service.createInventoryCheck(request.getInventoryCheckDTO(), request.getListInventoryCheckDetailDTO());
    }

    @GetMapping("/summary/{id}")
    public InventoryCheckResponse getInventoryCheckSummary(@PathVariable String id) {
        return service.getInventoryCheckSummary(id);
    }
}
