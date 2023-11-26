package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "report_revenue")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRevenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_revenue_id", nullable = false)
    private Long reportRevenueId;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "day_of_weeks", length = 50)
    private String dayOfWeeks;

    @Column(name = "revenue_value")
    private Float revenueValue;
}