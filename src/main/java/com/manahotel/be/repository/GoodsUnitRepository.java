package com.manahotel.be.repository;

import com.manahotel.be.model.entity.GoodsUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsUnitRepository extends JpaRepository<GoodsUnit, Long> {
    @Query("SELECT gu FROM GoodsUnit gu WHERE gu.goods.goodsId = :goodsId AND gu.isDefault = :isDefault")
    GoodsUnit findGoodsUnitByGoodsIdAndIsDefault(String goodsId, Boolean isDefault);


}
