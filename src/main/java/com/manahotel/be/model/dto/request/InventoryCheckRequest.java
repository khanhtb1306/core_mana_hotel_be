package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.InventoryCheckDTO;
import com.manahotel.be.model.dto.InventoryCheckDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class InventoryCheckRequest {
    private InventoryCheckDTO inventoryCheckDTO;
    private List<InventoryCheckDetailDTO> listInventoryCheckDetailDTO;
}
