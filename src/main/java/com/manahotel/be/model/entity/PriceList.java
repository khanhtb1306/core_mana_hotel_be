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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "price_list")
public class PriceList {
    @Id
    @Column(name = "price_list_id")
    private String priceListId;

    @Column(name = "price_list_name")
    private String priceListName;

    @Column(name = "effective_time_start")
    private Timestamp effectiveTimeStart;

    @Column(name = "effective_time_end")
    private Timestamp effectiveTimeEnd;

    @Column(name = "status")
    private Long status;

    @Column(name = "note")
    private String note;

}