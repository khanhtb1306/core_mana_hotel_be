package com.manahotel.be.repository;

import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.PriceListDetail;
import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceListDetailRepository extends JpaRepository<PriceListDetail, Long> {
    List<PriceListDetail> findPriceListDetailByPriceList(PriceList priceList);

    @Query("SELECT distinct roomCategory.roomCategoryId from PriceListDetail " +
            "where priceList = ?1")
    List<String> getDistinctRoomClassByPriceList(PriceList priceList);

    @Query("SELECT pld FROM PriceListDetail pld " +
            "where pld.roomCategory.roomCategoryId = ?1")
    List<PriceListDetail> getAllPriceListDetailByRoomCategoryId(String roomCategoryId);

}
