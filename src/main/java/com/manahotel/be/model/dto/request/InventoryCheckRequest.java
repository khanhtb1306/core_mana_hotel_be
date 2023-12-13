package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.InventoryCheckDTO;
import com.manahotel.be.model.dto.response.InventoryCheckDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class InventoryCheckRequest {
    private InventoryCheckDTO inventoryCheckDTO;
    private List<InventoryCheckDetailDTO> listInventoryCheckDetailDTO;
}
