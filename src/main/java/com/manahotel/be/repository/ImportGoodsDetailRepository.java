package com.manahotel.be.repository;

import com.manahotel.be.model.entity.ImportGoods;
import com.manahotel.be.model.entity.ImportGoodsDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportGoodsDetailRepository extends JpaRepository<ImportGoodsDetail, Long> {
    List<ImportGoodsDetail> findImportGoodsDetailByImportGoods(ImportGoods importGoods);
    List<ImportGoodsDetail> findImportGoodsDetailByImportGoods_ImportGoodsId(String importGoods);
    @Query(value = "DELETE FROM import_goods_detail igd WHERE igd.import_goods_id = :id", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteImportGoodsDetailByImportGoodsId(String id);

    @Query(value = "SELECT SUM(igd.total) FROM ImportGoodsDetail igd " +
            "where igd.importGoods = ?1")
    Float getSumOfImportGoodsDetails(ImportGoods importGoods);
}
