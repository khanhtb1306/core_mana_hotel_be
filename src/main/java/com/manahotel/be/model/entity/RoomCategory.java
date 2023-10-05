package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "room_category")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCategory {
    @Id
    @Column(name = "room_category_id", nullable = false, length = 50)
    private String roomCategoryId;

    @Column(name = "room_category_name", length = 250)
    private String roomCategoryName;

    @Column(name = "room_capacity")
    private Long roomCapacity;

    @Column(name = "room_area")
    private Float roomArea;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}