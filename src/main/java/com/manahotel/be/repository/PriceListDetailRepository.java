package com.manahotel.be.repository;

import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.PriceListDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceListDetailRepository extends JpaRepository<PriceListDetail, Long> {
    List<PriceListDetail> findPriceListDetailByPriceList(PriceList priceList);
}
