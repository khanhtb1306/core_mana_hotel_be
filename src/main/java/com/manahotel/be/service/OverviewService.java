package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public ResponseDTO getReportRoomCapacityCurrentMonth() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

            List<ReportRoomCapacity> reportRoomCapacities = reportRoomCapacityRepository.findAllInCurrentMonth();

            List<String> daysOfMonth = reportRoomCapacities.stream()
                    .map(report -> report.getCreateDate().toLocalDateTime().toLocalDate().format(formatter))
                    .collect(Collectors.toList());

            List<Float> roomCapacityValues = reportRoomCapacities.stream()
                    .map(report -> BigDecimal.valueOf(report.getRoomCapacityValue()).setScale(2, RoundingMode.HALF_UP).floatValue())
                    .collect(Collectors.toList());

            Map<String, Object> result = new HashMap<>();
            result.put("daysOfMonth", daysOfMonth);
            result.put("roomCapacityValues", roomCapacityValues);

            return ResponseUtils.success(result, "IsSuccess");
        } catch (Exception e) {
            log.error("Error retrieving report room capacities: {}", e.getMessage(), e);
            return ResponseUtils.error("IsFail");
        }
    }




    public ResponseDTO getReportRoomCapacityLastMonth() {
        try {
            List<ReportRoomCapacity> reportRoomCapacities = reportRoomCapacityRepository.findAllInLastMonth();
            return ResponseUtils.success(reportRoomCapacities,"IsSuccess");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error("IsFail");
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
        try {
            List<Room> listRoom = roomRepository.findByStatus(Status.ACTIVATE);
            long totalRooms = listRoom.size();
            long totalTimeUseOfRoomsMax = totalRooms * 24 * 60 * 60 * 1000;
            LocalDate today = LocalDate.now();
            DayOfWeek dayOfWeek = today.getDayOfWeek();
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
            reportRoomCapacity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            reportRoomCapacity.setDayOfWeek(dayOfWeek.toString());
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dateFormat.format(new Date(logTime.getTime()));
        reportRevenue.setDayOfWeeks(dayOfWeek);

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
        result.put("daysOfMonth", daysOfMonth);
        result.put("revenueValues", revenueValues);

        return ResponseUtils.success(result, "Hiển thị doanh thu từng ngày theo tháng thành công");
    }
}
