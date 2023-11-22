package com.manahotel.be.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recent_activity")
public class RecentActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recent_activity_id")
    private Integer recentActivityId;

    @Column(name = "staff_name")
    private String staffName;

    @Column(name = "action")
    private String action;

    @Column(name = "value")
    private Float value;

    @Column(name = "create_time")
    private Timestamp createTime;
}
