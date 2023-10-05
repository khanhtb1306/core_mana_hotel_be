package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "price_list", indexes = {
        @Index(name = "room_category_id_idx", columnList = "room_category_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_list_id", nullable = false)
    private Long priceListId;

    @Column(name = "price_list_name", length = 250)
    private String priceListName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_category_id", nullable = false)
    private RoomCategory roomCategory;

    @Column(name = "price_by_day", nullable = false)
    private Float priceByDay;

    @Column(name = "price_by_night", nullable = false)
    private Float priceByNight;

    @Column(name = "price_by_hour", nullable = false)
    private Float priceByHour;

    @Column(name = "status")
    private Boolean status;

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