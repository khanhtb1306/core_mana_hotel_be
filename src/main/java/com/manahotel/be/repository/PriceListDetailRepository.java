package com.manahotel.be.repository;

import com.manahotel.be.model.entity.PriceList;
import com.manahotel.be.model.entity.PriceListDetail;
import com.manahotel.be.model.entity.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PriceListDetailRepository extends JpaRepository<PriceListDetail, Long> {
    List<PriceListDetail> findPriceListDetailByPriceList(PriceList priceList);

    @Query("SELECT DISTINCT pld.roomCategory.roomCategoryId FROM PriceListDetail pld " +
            "WHERE pld.priceList = ?1")
    List<String> getDistinctRoomClassByPriceList(PriceList priceList);


    @Query("SELECT pld FROM PriceListDetail pld WHERE pld.roomCategory.roomCategoryId = ?1 and pld.priceList.priceListId = ?2")
    List<PriceListDetail> getAllPriceListDetailByRoomCategoryId(String roomCategoryId, String priceListId);

    @Query("SELECT pld FROM PriceListDetail pld WHERE pld.priceList.priceListId = ?1")
    List<PriceListDetail> getAllPriceListDetailByPriceListId(String priceListId);

}