package com.manahotel.be.service;

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
