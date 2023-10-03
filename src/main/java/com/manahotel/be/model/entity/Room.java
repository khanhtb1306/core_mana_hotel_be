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
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @Column(name = "room_id")
    private String roomId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "roomCategoryId")
    private String roomCategoryId;

    @Column(name = "floor")
    private String floor;

    @Column(name = "status")
    private boolean status;

    @Column(name = "booking_status")
    private Long bookingStatus;

    @Column(name = "condition_status")
    private Long conditionStatus;

    @Column(name = "note")
    private String note;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}
