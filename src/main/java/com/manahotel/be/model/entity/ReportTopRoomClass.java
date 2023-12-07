package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report_top_room_class")
public class ReportTopRoomClass {
    @Id
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
}
