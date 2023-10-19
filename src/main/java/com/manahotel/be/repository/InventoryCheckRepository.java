package com.manahotel.be.repository;

import com.manahotel.be.model.entity.InventoryCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryCheckRepository extends JpaRepository<InventoryCheck, String> {
    InventoryCheck findTopByOrderByInventoryCheckIdDesc();

    @Query("SELECT ic FROM InventoryCheck ic " +
            "LEFT JOIN InventoryCheckDetail icd ON icd.inventoryCheck.inventoryCheckId = ic.inventoryCheckId")
    List<Object[]> findAllInventoryChecksWithDetails();
}
