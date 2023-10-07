package com.manahotel.be.controller;

import com.manahotel.be.model.dto.InventoryCheckDTO;
import com.manahotel.be.model.dto.InventoryCheckDetailDTO;
import com.manahotel.be.model.entity.InventoryCheck;
import com.manahotel.be.service.InventoryCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory-check")
public class InventoryCheckController {

    @Autowired
    private InventoryCheckService service;

    @PostMapping
    public InventoryCheck createInventoryCheck(@RequestBody InventoryCheckDTO inventoryCheckDTO,
                                               @RequestBody(required = false) List<InventoryCheckDetailDTO> listInventoryCheckDetailDTO) {
        return service.createInventoryCheck(inventoryCheckDTO, listInventoryCheckDetailDTO);
    }
}
