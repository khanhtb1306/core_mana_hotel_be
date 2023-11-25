package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    Goods findTopByOrderByGoodsIdDesc();

    @Query("SELECT g FROM Goods g " +
            "LEFT JOIN GoodsUnit gu ON g.goodsId = gu.goods.goodsId where g.status <> 6")
    List<Object[]> findGoodWithGoodUnits();

    List<Goods> findGoodsByGoodsCategory(boolean goodsCategory);

    @Query(value = "SELECT COUNT(*) > 0 FROM goods g WHERE g.goods_name = ?1 AND g.status = 1 AND g.goods_id <> ?2", nativeQuery = true)
    boolean existsByGoodsNameAndNotId(String goodsName, String goodsId);
}
