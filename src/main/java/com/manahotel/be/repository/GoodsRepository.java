package com.manahotel.be.repository;

import com.manahotel.be.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    Goods findTopByOrderByGoodsIdDesc();
}
