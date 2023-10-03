package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "price_list")
@NoArgsConstructor
@AllArgsConstructor
public class PriceList {
    @Id
    @Column(name = "price_list_id")
    private Long priceListId;

    @Column(name = "price_list_name")
    private Long priceListName;

    @Column(name = "room_category_id")
    private String roomCategoryId;

    @Column(name = "price_by_day")
    private Float priceByDay;

    @Column(name = "price_by_night")
    private Float priceByNight;

    @Column(name = "price_by_hour")
    private Float priceByHour;

    @Column(name = "status")
    private boolean status;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}
