package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsUnitRepository extends JpaRepository<GoodsUnit, Long> {
    @Query("SELECT gu FROM GoodsUnit gu WHERE gu.goods.goodsId = :goodsId AND gu.isDefault = :isDefault")
    GoodsUnit findGoodsUnitByGoodsIdAndIsDefault(String goodsId, Boolean isDefault);

    List<GoodsUnit> findGoodsUnitByGoods(Goods goods);

    @Query("SELECT gu FROM GoodsUnit gu WHERE gu.goods.goodsId = :goodsId AND gu.isDefault = :isDefault")
    List<GoodsUnit> findGoodsUnitByGoodsIdAndAndIsDefault(String goodsId, Boolean isDefault);

    @Query("select gu FROM GoodsUnit gu " +
            "where gu.goods.goodsId = ?1 and gu.status <> 6")
    List<GoodsUnit> getGoodsUnitByGoodsIdNotStatus6(String goodsId);

    List<GoodsUnit> findByStatusNot(Long status);
}
