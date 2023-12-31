package com.manahotel.be.repository;

import com.manahotel.be.model.entity.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends JpaRepository<PriceList, String> {

    PriceList findTopByOrderByPriceListIdDesc();

}