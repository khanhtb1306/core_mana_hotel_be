package com.manahotel.be.model.dto.response;

import lombok.Data;

import java.sql.Time;
@Data
public class TimeUseDTO {
    private Long timeUseId;
    private Time endTimeNight;
    private Time startTimeDay;
    private Time endTimeDay;
    private Time startTimeNight;
    private String timeBonusDayType;
    private Long timeBonusHour;
    private Long timeBonusDay;
}
