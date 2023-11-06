package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Table(name = "price_list_detail", indexes = {
        @Index(name = "pk_pld_pl_idx", columnList = "price_list_id"),
        @Index(name = "pk_pld_rc_idx", columnList = "room_category_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceListDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_list_detail_id")
    private Long priceListDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "price_list_id", nullable = false)
    private PriceList priceList;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_category_id", nullable = false)
    private RoomCategory roomCategory;

    @Column(name = "price_by_day")
    private Float priceByDay;

    @Column(name = "price_by_night")
    private Float priceByNight;

    @Column(name = "price_by_hour")
    private Float priceByHour;

    @Column(name = "time_apply")
    private Timestamp timeApply;

    @Column(name = "day_of_week")
    private String dayOfWeek;
}