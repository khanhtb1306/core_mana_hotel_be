package com.manahotel.be.service;

import com.manahotel.be.model.dto.InventoryCheckDTO;
import com.manahotel.be.model.dto.InventoryCheckDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class InventoryCheckRequest {
    private InventoryCheckDTO inventoryCheckDTO;
    private List<InventoryCheckDetailDTO> listInventoryCheckDetailDTO;
}
