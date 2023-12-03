package com.manahotel.be.common.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Calendar;

public class DateUtil {
    public static Timestamp calculateTimestamp(Timestamp baseTimestamp, Time time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseTimestamp);
        calendar.set(Calendar.HOUR_OF_DAY, time.getHours());
        calendar.set(Calendar.MINUTE, time.getMinutes());
        calendar.set(Calendar.SECOND, time.getSeconds());
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static int calculateDurationInHours(Timestamp start, Timestamp end) {
        long timeDifferenceMillis = Math.abs(start.getTime() - end.getTime());
        Duration duration = Duration.ofMillis(timeDifferenceMillis);
        return (int) duration.toHours();
    }

    public static Timestamp addHoursToTimestamp(Timestamp timestamp, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
