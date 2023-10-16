package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "room", indexes = {
        @Index(name = "pk_r_rc_idx", columnList = "room_category_id"),
        @Index(name = "pk_r_f_idx", columnList = "floor_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @Column(name = "room_id", nullable = false, length = 50)
    private String roomId;

    @Column(name = "room_name", length = 250)
    private String roomName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_category_id", nullable = false)
    private RoomCategory roomCategory;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @Column(name = "status")
    private Long status;

    @Column(name = "booking_status")
    private Long bookingStatus;

    @Column(name = "condition_status")
    private Long conditionStatus;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "note", length = 250)
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