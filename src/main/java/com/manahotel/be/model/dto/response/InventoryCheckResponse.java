package com.manahotel.be.model.dto.response;

public interface InventoryCheckResponse {
    Long getTotalActualInventory();

    Float getTotalActualInventoryValue();

    Long getTotalIncreaseDiscrepancy();

    Float getTotalIncreaseDiscrepancyValue();

    Long getTotalDecreaseDiscrepancy();

    Float getTotalDecreaseDiscrepancyValue();

    Long getTotalQuantityDiscrepancy();

    Float getTotalQuantityDiscrepancyValue();
}
