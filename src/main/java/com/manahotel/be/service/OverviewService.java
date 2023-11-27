package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OverviewService {

    @Autowired
    private RecentActivityRepository recentActivityRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReportRoomCapacityRepository reportRoomCapacityRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private ReportRevenueRepository reportRevenueRepository;

    @Autowired
    private FundBookRepository fundBookRepository;

        public ResponseDTO getReportRoomCapacityByMonth(String dateString) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate date = LocalDate.parse(dateString, formatter);

                DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
                List<String> allDaysOfMonth = new ArrayList<>();
                YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonthValue());
                int lengthOfMonth = yearMonth.lengthOfMonth();
                for (int day = 1; day <= lengthOfMonth && LocalDate.of(date.getYear(), date.getMonth(), day).isBefore(LocalDate.now()); day++) {
                    allDaysOfMonth.add(String.format("%02d", day));
                }

                List<ReportRoomCapacity> reportRoomCapacities = reportRoomCapacityRepository.findAllInCurrentMonth(date.getMonthValue(), date.getYear());
                Map<String, Float> roomCapacityMap = new HashMap<>();

                reportRoomCapacities.forEach(report -> {
                    LocalDate reportDate = report.getCreateDate().toLocalDateTime().toLocalDate();
                    if (!reportDate.isAfter(LocalDate.now())) {
                        String dayOfMonth = reportDate.format(dayFormatter);
                        Float roomCapacityValue = BigDecimal.valueOf(report.getRoomCapacityValue()).setScale(2, RoundingMode.HALF_UP).floatValue();
                        roomCapacityMap.put(dayOfMonth, roomCapacityValue);
                    }
                });
                allDaysOfMonth.forEach(day -> {
                    if (LocalDate.of(date.getYear(), date.getMonth(), Integer.parseInt(day)).isBefore(LocalDate.now())) {
                        roomCapacityMap.putIfAbsent(day, 0.0f);
                    }
                });

                List<String> sortedDaysOfMonth = new ArrayList<>(roomCapacityMap.keySet());
                sortedDaysOfMonth.sort(Comparator.naturalOrder());

                List<Float> roomCapacityValues = sortedDaysOfMonth.stream()
                        .map(roomCapacityMap::get)
                        .collect(Collectors.toList());

                Map<String, Object> result = new HashMap<>();
                result.put("label", sortedDaysOfMonth);
                result.put("data", roomCapacityValues);

                return ResponseUtils.success(result, "IsSuccess");
            } catch (Exception e) {
                log.error("Error retrieving report room capacities: {}", e.getMessage(), e);
                return ResponseUtils.error("IsFail");
            }
        }

    public ResponseDTO getReportRoomCapacityByYear(Integer year) {
        try {
            List<Object[]> reportRoomCapacities = reportRoomCapacityRepository.findAverageRoomCapacityByYear(year);
            List<String> months = new ArrayList<>();
            List<Float> averageRoomCapacities = new ArrayList<>();
            LocalDate currentDate = LocalDate.now();
            Map<Integer, Double> monthDataMap = new HashMap<>();
            for (Object[] row : reportRoomCapacities) {
                int month = (int) row[1];
                double averageRoomCapacity = (double) row[2];
                monthDataMap.put(month, averageRoomCapacity);
            }
            for (int month = 1; month <= currentDate.getMonthValue(); month++) {
                String monthString = String.format("%02d", month);
                months.add(monthString);
                double averageRoomCapacity = monthDataMap.getOrDefault(month, 0.0);
                BigDecimal bd = new BigDecimal(averageRoomCapacity).setScale(2, RoundingMode.HALF_UP);
                averageRoomCapacities.add(bd.floatValue());
            }
            Map<String, List<?>> result = new HashMap<>();
            result.put("label", months);
            result.put("data", averageRoomCapacities);

            return ResponseUtils.success(result, "IsSuccess");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.error("IsFail");
        }
    }

    public ResponseDTO getReportRoomCapacityWithDayOfWeekByMonth(String dateString, boolean check) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(dateString, formatter);
            List<Object[]> reportRoomCapacities;
            if(check){
                reportRoomCapacities = reportRoomCapacityRepository.findRoomCapacityWithDayOfWeekByMonth(date.getMonthValue(), date.getYear());
            }else {
                reportRoomCapacities = reportRoomCapacityRepository.findRoomCapacityWithDayOfWeekByMonth(null, date.getYear());
            }
            List<String> listDayOfWeek = new ArrayList<>();
            List<Float> averageRoomCapacities = new ArrayList<>();

            for (Object[] row : reportRoomCapacities) {
                int numericalDayOfWeek = ((Number) row[0]).intValue();
                String dayOfWeek = getDayOfWeekNameInVietnamese(numericalDayOfWeek);
                double averageRoomCapacity = ((Number) row[1]).doubleValue();

                listDayOfWeek.add(dayOfWeek);

                BigDecimal bd = new BigDecimal(averageRoomCapacity).setScale(2, RoundingMode.HALF_UP);
                averageRoomCapacities.add(bd.floatValue());
            }

            Map<String, List<?>> result = new HashMap<>();
            result.put("label", listDayOfWeek);
            result.put("data", averageRoomCapacities);

            return ResponseUtils.success(result, "IsSuccess");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.error("IsFail");
        }
    }

    private String getDayOfWeekNameInVietnamese(int numericalDayOfWeek) {
        switch (numericalDayOfWeek) {
            case 1:
                return "Chủ nhật";
            case 2:
                return "Thứ hai";
            case 3:
                return "Thứ ba";
            case 4:
                return "Thứ tư";
            case 5:
                return "Thứ năm";
            case 6:
                return "Thứ sáu";
            case 7:
                return "Thứ bảy";
            default:
                return "Không xác định";
        }
    }

    public ResponseDTO getRoomCapacity() {
        try {
            List<Object[]> roomCapacityList = roomRepository.getTotalAndEmptyRoomCounts();
            if (roomCapacityList != null && !roomCapacityList.isEmpty()) {
                Object[] roomCapacityArray = roomCapacityList.get(0);

                long totalRooms = (long) roomCapacityArray[0];
                long roomsInUse = (long) roomCapacityArray[1];
                long emptyRooms = (long) roomCapacityArray[2];

                double percentageRoomsInUse = (double) roomsInUse / totalRooms * 100;
                double percentageEmptyRooms = (double) emptyRooms / totalRooms * 100;

                // Đưa vào Map
                Map<String, Object> roomCapacityMap = new HashMap<>();
                roomCapacityMap.put("totalRooms", totalRooms);
                roomCapacityMap.put("roomsInUse", roomsInUse);
                roomCapacityMap.put("emptyRooms", emptyRooms);
                roomCapacityMap.put("percentageRoomsInUse", percentageRoomsInUse);
                roomCapacityMap.put("percentageEmptyRooms", percentageEmptyRooms);

                return ResponseUtils.success(roomCapacityMap, "isSuccess");
            } else {
                log.info("Fail get capacity array");
                return ResponseUtils.error("isFail");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.error("isFail");
        }
    }

    public ResponseDTO getRecentActivity(){
        try{
            LocalDateTime endDate = LocalDateTime.now();
            LocalDateTime startDate = endDate.minus(7, ChronoUnit.DAYS);
            List<RecentActivity> recentActivities = recentActivityRepository.findByCreateTimeGreaterThanEqualOrderByCreateTimeDesc(startDate);
            return ResponseUtils.success(recentActivities, "isSuccess");

        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error( "isFail");
        }
    }

    public void writeRecentActivity(String staff_name, String action, float value, Timestamp create_time){
        try{
            RecentActivity recentActivity = RecentActivity.builder()
                    .staffName(staff_name)
                    .action(action)
                    .value(value)
                    .createTime(create_time)
                    .build();
            recentActivityRepository.save(recentActivity);
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
        }
    }

//        @Scheduled(initialDelay = 0, fixedDelay = 100000)
    @Scheduled(cron = "59 58 23 * * ?")  // Chạy vào 23:59 hàng ngày
    public void checkRoomCapacityDaily() {
        log.info("----- Check Room Capacity Daily Start ------");
        Timestamp logTime = new Timestamp(System.currentTimeMillis());

        try {
            List<Room> listRoom = roomRepository.findByStatus(Status.ACTIVATE);
            long totalRooms = listRoom.size();
            long totalTimeUseOfRoomsMax = totalRooms * 24 * 60 * 60 * 1000;
            LocalDate today = LocalDate.now();
            long totalTimeUseToday = 0;

            for (Room room : listRoom) {
                long totalTimestamp = 0;

                List<ReservationDetail> rdList = reservationDetailRepository.checkRoomCapacityDaily(room.getRoomId());

                for (ReservationDetail rd : rdList) {

                    long timestamp = 0;

                    LocalDate checkInDate = rd.getCheckInEstimate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    if (rd.getStatus().equals(Status.CHECK_IN)) {
                        if (!checkInDate.equals(today)) {
                            timestamp = 24 * 60 * 60 * 1000;
                        } else {
                            long DurationCheckIn;
                            if(rd.getCheckInEstimate().getTime() >= rd.getCheckInActual().getTime()){
                                DurationCheckIn = ChronoUnit.MILLIS.between(
                                        rd.getCheckInEstimate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckInEstimate().toInstant().atZone(ZoneId.systemDefault()));
                            }else {
                                DurationCheckIn = ChronoUnit.MILLIS.between(
                                        rd.getCheckInActual().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckInActual().toInstant().atZone(ZoneId.systemDefault()));
                            }
                            timestamp = (24 * 60 * 60 * 1000) - DurationCheckIn;
                        }
                    } else if (rd.getStatus().equals(Status.CHECK_OUT)) {

                        if (!checkInDate.equals(today)) {
                            if(rd.getCheckOutEstimate().getTime() >= rd.getCheckInActual().getTime()) {
                                timestamp = ChronoUnit.MILLIS.between(
                                        rd.getCheckOutEstimate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckOutEstimate().toInstant().atZone(ZoneId.systemDefault()));
                            }else {
                                timestamp = ChronoUnit.MILLIS.between(
                                        rd.getCheckOutActual().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckOutActual().toInstant().atZone(ZoneId.systemDefault()));
                            }
                        } else {
                            long DurationCheckOutActual;
                            if(rd.getCheckInActual().getTime() >= rd.getCheckOutEstimate().getTime()){
                                DurationCheckOutActual = ChronoUnit.MILLIS.between(
                                        rd.getCheckOutActual().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckOutActual().toInstant().atZone(ZoneId.systemDefault()));
                            }else {
                                DurationCheckOutActual = ChronoUnit.MILLIS.between(
                                        rd.getCheckOutEstimate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckOutEstimate().toInstant().atZone(ZoneId.systemDefault()));
                            }
                            long DurationCheckInEstimate;
                            if(rd.getCheckInEstimate().getTime() >= rd.getCheckInActual().getTime()){
                                DurationCheckInEstimate = ChronoUnit.MILLIS.between(
                                        rd.getCheckInEstimate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckInEstimate().toInstant().atZone(ZoneId.systemDefault()));
                            }else {
                                DurationCheckInEstimate = ChronoUnit.MILLIS.between(
                                        rd.getCheckInActual().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                                        rd.getCheckOutActual().toInstant().atZone(ZoneId.systemDefault()));
                            }
                            timestamp = DurationCheckOutActual - DurationCheckInEstimate;
                        }
                    }
                    totalTimestamp += timestamp;
                }
                totalTimeUseToday += totalTimestamp;
            }

            float roomCapacityToday = ((float) totalTimeUseToday/totalTimeUseOfRoomsMax)*100;
            ReportRoomCapacity reportRoomCapacity = new ReportRoomCapacity();
            reportRoomCapacity.setRoomCapacityValue(roomCapacityToday);
            reportRoomCapacity.setCreateDate(logTime);
            reportRoomCapacityRepository.save(reportRoomCapacity);

            log.info("----- Check Room Capacity Daily End ------");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void checkRevenueDaily() {
        Timestamp logTime = new Timestamp(System.currentTimeMillis());
        Float totalIncome = fundBookRepository.getAllExpenseByDay(logTime);

        ReportRevenue reportRevenue = new ReportRevenue();
        reportRevenue.setCreatedDate(logTime);
        reportRevenue.setRevenueValue(totalIncome != null ? totalIncome : 0);

        reportRevenueRepository.save(reportRevenue);
    }

    public ResponseDTO getReportRevenueEachDayByMonth(Integer month) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        List<ReportRevenue> reportRevenues = reportRevenueRepository.findAllByMonth(month);

        List<String> daysOfMonth = reportRevenues.stream()
                .map(report -> report.getCreatedDate().toLocalDateTime().toLocalDate().format(formatter))
                .collect(Collectors.toList());

        List<Float> revenueValues = reportRevenues.stream()
                .map(report -> BigDecimal.valueOf(report.getRevenueValue()).floatValue())
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("label", daysOfMonth);
        result.put("data", revenueValues);

        return ResponseUtils.success(result, "Hiển thị doanh thu từng ngày theo tháng thành công");
    }

    public ResponseDTO getReportRevenueDayOfWeekByMonthOrYear(String time, boolean isSearchByMonth) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(time, formatter);
        List<ReportRevenue> reportRevenues = isSearchByMonth ? reportRevenueRepository.findAllByMonth(date.getMonthValue()) : reportRevenueRepository.findAllByYear(date.getYear());

        DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE");

        Map<String, Float> dayOfWeeksRevenueSum = reportRevenues.stream()
                .collect(Collectors.groupingBy(
                        report -> report.getCreatedDate().toLocalDateTime().format(dayOfWeekFormatter),
                        Collectors.summingDouble(report -> BigDecimal.valueOf(report.getRevenueValue()).doubleValue())
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().floatValue()
                ));

        List<String> daysOfWeek = new ArrayList<>(dayOfWeeksRevenueSum.keySet());
        List<Float> revenueValues = new ArrayList<>(dayOfWeeksRevenueSum.values());

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("label", daysOfWeek);
        result.put("data", revenueValues);

        return ResponseUtils.success(result, isSearchByMonth ? "Hiển thị doanh thu từng thứ theo tháng thành công" : "Hiển thị doanh thu từng thứ theo năm thành công");
    }

    public ResponseDTO getReportRevenueMonthByYear(Integer year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        List<ReportRevenue> reportRevenues = reportRevenueRepository.findAllByYear(year);

        Map<String, Float> monthsOfYearRevenueSum = reportRevenues.stream()
                .collect(Collectors.groupingBy(
                        report -> report.getCreatedDate().toLocalDateTime().format(formatter),
                        Collectors.summingDouble(report -> BigDecimal.valueOf(report.getRevenueValue()).doubleValue())
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().floatValue()
                ));

        List<String> monthsOfYear = new ArrayList<>(monthsOfYearRevenueSum.keySet());
        List<Float> revenueValues = new ArrayList<>(monthsOfYearRevenueSum.values());

        Map<String, Object> result = new HashMap<>();
        result.put("label", monthsOfYear);
        result.put("data", revenueValues);

        return ResponseUtils.success(result, "Hiển thị doanh thu từng tháng theo năm thành công");
    }
}
