package com.manahotel.be.repository;

import com.manahotel.be.model.entity.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, String> {

    GoodsCategory findTopByOrderByGoodsCategoryIdDesc();
}
