package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report_room_capacity")
public class ReportRoomCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_room_capacity_id")
    private Integer reportRoomCapacityId;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "room_capacity_value")
    private Float roomCapacityValue;
}
