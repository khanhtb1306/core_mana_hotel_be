package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "time_use")
public class TimeUse {
    @Id
    @Column(name = "time_use_id")
    private Long timeUseId;

    @Column(name = "end_time_night")
    private Time endTimeNight;

    @Column(name = "start_time_day")
    private Time startTimeDay;

    @Column(name = "end_time_day")
    private Time endTimeDay;

    @Column(name = "start_time_night")
    private Time startTimeNight;

    @Column(name = "time_bonus_day_type")
    private String timeBonusDayType;

    @Column(name = "time_bonus_hour")
    private Long timeBonusHour;

    @Column(name = "time_bonus_day")
    private Long timeBonusDay;

}
