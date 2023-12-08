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
@Table(name = "report_top_room_class")
public class ReportTopRoomClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_top_room_class_id")
    private Long reportTopRoomClassId;

    @Column(name = "room_class_id")
    private String roomClassId;

    @Column(name = "revenue")
    private Float revenue;

    @Column(name = "number_of_rental")
    private Long numberOfRental;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "update_date")
    private Timestamp updateDate;
}
