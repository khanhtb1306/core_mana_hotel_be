package com.manahotel.be.repository;

import com.manahotel.be.model.entity.InventoryCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryCheckRepository extends JpaRepository<InventoryCheck, String> {
    InventoryCheck findTopByOrderByInventoryCheckIdDesc();
}
