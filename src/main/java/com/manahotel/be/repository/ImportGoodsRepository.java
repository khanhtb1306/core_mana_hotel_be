package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ImportGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportGoodsRepository extends JpaRepository<ImportGoods, String> {
    ImportGoods findTopByOrderByImportGoodsIdDesc();

    @Query("SELECT ig FROM ImportGoods ig " +
            "LEFT JOIN ImportGoodsDetail igd ON igd.importGoods.importGoodsId = ig.importGoodsId")
    List<Object[]> findAllImportGoodsWithDetails();
}
