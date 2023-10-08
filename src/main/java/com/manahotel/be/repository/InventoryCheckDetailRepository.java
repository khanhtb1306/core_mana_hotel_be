package com.manahotel.be.repository;

import com.manahotel.be.model.entity.InventoryCheckDetail;
import com.manahotel.be.service.InventoryCheckResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryCheckDetailRepository extends JpaRepository<InventoryCheckDetail, Long> {
    @Query(value = "SELECT " +
            "SUM(icd.actual_inventory) AS totalActualInventory, " +
            "SUM(icd.actual_inventory * icd.cost) AS totalActualInventoryValue, " +
            "SUM(CASE WHEN icd.quantity_discrepancy > 0 THEN icd.quantity_discrepancy ELSE 0 END) AS totalIncreaseDiscrepancy, " +
            "SUM(CASE WHEN icd.quantity_discrepancy > 0 THEN icd.quantity_discrepancy * icd.cost ELSE 0 END) AS totalIncreaseDiscrepancyValue, " +
            "SUM(CASE WHEN icd.quantity_discrepancy < 0 THEN icd.quantity_discrepancy ELSE 0 END) AS totalDecreaseDiscrepancy, " +
            "SUM(CASE WHEN icd.quantity_discrepancy < 0 THEN icd.quantity_discrepancy * icd.cost ELSE 0 END) AS totalDecreaseDiscrepancyValue, " +
            "SUM(icd.quantity_discrepancy) AS totalQuantityDiscrepancy, " +
            "SUM(icd.quantity_discrepancy * icd.cost) AS totalQuantityDiscrepancyValue " +
            "FROM inventory_check_detail icd WHERE icd.inventory_check_id = :id", nativeQuery = true)
    InventoryCheckResponse getInventoryCheckSummary(String id);
}
