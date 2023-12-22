package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
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

    @Autowired
    private RoomClassRepository roomClassRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ControlPolicyRepository controlPolicyRepository;

    @Autowired
    private ReportTopRoomClassRepository reportTopRoomClassRepository;

        public ResponseDTO getReportRoomCapacityByMonth(String dateString) {
            log.info("Get Report Room Capacity By Month Start");
            try {
                checkRoomCapacityDaily();
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
                log.info("Get Report Room Capacity By Month End");
                return ResponseUtils.success(result, "IsSuccess");
            } catch (Exception e) {
                log.error("Error retrieving report room capacities: {}", e.getMessage(), e);
                return ResponseUtils.error("IsFail");
            }
        }

    public ResponseDTO getReportRoomCapacityByManyYear(Integer startYear) {
        log.info("Get Report Room Capacity By Many Year Start");
        try {
            ZoneId vietnamTimeZone = ZoneId.of("Asia/Ho_Chi_Minh");
            int currentYear = Year.now(vietnamTimeZone).getValue();
            List<Object[]> reportRoomCapacities = reportRoomCapacityRepository.findRoomCapacityByYearRange(startYear);
            List<String> years = new ArrayList<>();
            List<Float> roomCapacities = new ArrayList<>();
            Map<Integer, Double> roomCapacitiesMap = new HashMap<>();
            for (Object[] result : reportRoomCapacities) {
                Integer year = (Integer) result[0];
                Double averageRoomCapacity = (Double) result[1];
                roomCapacitiesMap.put(year, averageRoomCapacity);
            }
            for (int year = startYear; year <= currentYear; year++) {
                years.add(Integer.toString(year));
                Double averageRoomCapacity = roomCapacitiesMap.getOrDefault(year, 0.0);
                BigDecimal bd = new BigDecimal(averageRoomCapacity).setScale(2, RoundingMode.HALF_UP);
                roomCapacities.add(bd.floatValue());
            }
            Map<String, List<?>> resultMap = new HashMap<>();
            resultMap.put("label", years);
            resultMap.put("data", roomCapacities);
            log.info("Get Report Room Capacity By Many Year End");
            return ResponseUtils.success(resultMap, "IsSuccess");
        } catch (Exception e) {
            log.error("getReportRoomCapacityByManyYear_isFail" + e.getMessage());
            return ResponseUtils.error("IsFail");
        }
    }


    public ResponseDTO getReportRoomCapacityByYear(Integer year) {
        log.info("Get Report Room Capacity By Year Start");
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
            log.info("Get Report Room Capacity By Year End");
            return ResponseUtils.success(result, "IsSuccess");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.error("IsFail");
        }
    }

    public ResponseDTO getReportRoomCapacityWithDayOfWeekByMonth(String dateString, boolean check) {
        log.info("Get Report Room Capacity With DayOfWeek By Month Start");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(dateString, formatter);
            List<Object[]> reportRoomCapacities;
            if (check) {
                reportRoomCapacities = reportRoomCapacityRepository.findRoomCapacityWithDayOfWeekByMonth(date.getMonthValue(), date.getYear());
            } else {
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

            // Di chuyển ngày chủ nhật (1) xuống cuối danh sách
            if (!listDayOfWeek.isEmpty() && listDayOfWeek.get(0).equals("Chủ nhật")) {
                String firstDayOfWeek = listDayOfWeek.remove(0);
                listDayOfWeek.add(firstDayOfWeek);

                Float firstAverageRoomCapacity = averageRoomCapacities.remove(0);
                averageRoomCapacities.add(firstAverageRoomCapacity);
            }

            Map<String, List<?>> result = new HashMap<>();
            result.put("label", listDayOfWeek);
            result.put("data", averageRoomCapacities);
            log.info("Get Report Room Capacity With DayOfWeek By Month End");
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
        log.info("Get Room Capacity Start");
        try {
            List<Object[]> roomCapacityList = roomRepository.getTotalAndEmptyRoomCounts();
            Map<String, Object> roomCapacityMap = new HashMap<>();
            if (roomCapacityList != null && !roomCapacityList.isEmpty()) {
                Object[] roomCapacityArray = roomCapacityList.get(0);

                long totalRooms = (long) roomCapacityArray[0];
                long roomsInUse = (long) roomCapacityArray[1];
                long emptyRooms = (long) roomCapacityArray[2];

                double percentageRoomsInUse = (double) roomsInUse / totalRooms * 100;
                double percentageEmptyRooms = (double) emptyRooms / totalRooms * 100;

                // Đưa vào Map
                roomCapacityMap.put("totalRooms", totalRooms);
                roomCapacityMap.put("roomsInUse", roomsInUse);
                roomCapacityMap.put("emptyRooms", emptyRooms);
                roomCapacityMap.put("percentageRoomsInUse", percentageRoomsInUse);
                roomCapacityMap.put("percentageEmptyRooms", percentageEmptyRooms);
            }
            log.info("Get Room Capacity End");
            return ResponseUtils.success(roomCapacityMap, "isSuccess");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.error("isFail");
        }
    }

    public ResponseDTO getRecentActivity(){
            log.info("Get Recent Activity Start");
        try{
            LocalDateTime endDate = LocalDateTime.now();
            LocalDateTime startDate = endDate.minus(7, ChronoUnit.DAYS);
            List<RecentActivity> recentActivities = recentActivityRepository.findByCreateTimeGreaterThanEqualOrderByCreateTimeDesc(startDate);
            log.info("Get Recent Activity End");
            return ResponseUtils.success(recentActivities, "isSuccess");
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseUtils.error( "isFail");
        }
    }

    public void writeRecentActivity(String staff_name, String action, float value, Timestamp create_time){
        log.info("Write Recent Activity Start");
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
        log.info("Write Recent Activity End");
    }
    @Scheduled(cron = "59 58 23 * * ?")
    public void checkRoomCapacityDailyEndDate() {
        checkRoomCapacityDaily();
    }

    public void checkRoomCapacityDaily() {
        log.info("----- Check Room Capacity Daily Start ------");
        Timestamp logTime = new Timestamp(System.currentTimeMillis());
        long currentTime = ChronoUnit.MILLIS.between(
                logTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(),
                logTime.toInstant().atZone(ZoneId.systemDefault()));
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
                            timestamp = currentTime;
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
                            timestamp = currentTime - DurationCheckIn;
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
                            if(rd.getCheckOutActual().getTime() >= rd.getCheckOutEstimate().getTime()){
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
            ReportRoomCapacity reportRoomCapacity = reportRoomCapacityRepository.findTodayReportRoomCapacity();
            if (reportRoomCapacity == null) {
                reportRoomCapacity = new ReportRoomCapacity();
            }
            reportRoomCapacity.setRoomCapacityValue(roomCapacityToday);
            reportRoomCapacity.setCreateDate(logTime);
            reportRoomCapacityRepository.save(reportRoomCapacity);

            log.info("----- Check Room Capacity Daily End ------");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    @Scheduled(cron = "59 59 23 * * *")
    public void checkRevenueEndDate() {
        checkRevenueDaily();
    }
    public void checkRevenueDaily() {
        log.info("----- Check Revenue Daily Start ------");
        try{
           Timestamp logTime = new Timestamp(System.currentTimeMillis());
           Float totalIncome = fundBookRepository.getAllIncomeByDay(logTime);

           ReportRevenue reportRevenue = new ReportRevenue();
           reportRevenue.setCreatedDate(logTime);
           reportRevenue.setRevenueValue(totalIncome != null ? totalIncome : 0);

           reportRevenueRepository.save(reportRevenue);
       }catch (Exception e){
            log.error("checkRevenueDaily_isFail" + e.getMessage());
       }
        log.info("----- Check Revenue Daily End ------");
    }

    public ResponseDTO getReportRevenueEachDayByMonth(String dateString) {
        log.info("----- Get Report Revenue Each Day By Month Start ------");
        try {
            checkRevenueDaily();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(dateString, formatDate);
            LocalDate currentDate = LocalDate.now();

            List<ReportRevenue> reportRevenues = reportRevenueRepository.findAllByMonth(date.getMonthValue(), date.getYear());

            Map<Integer, Float> dailyRevenueMap = new HashMap<>();

            reportRevenues.forEach(report -> {
                int dayOfMonth = report.getCreatedDate().toLocalDateTime().getDayOfMonth();
                float revenueValue = BigDecimal.valueOf(report.getRevenueValue()).floatValue();
                dailyRevenueMap.put(dayOfMonth, revenueValue);
            });

            int daysInMonth = date.lengthOfMonth();

            for (int day = 1; day <= daysInMonth; day++) {
                LocalDate currentDateWithDay = currentDate.withDayOfMonth(day);
                if (currentDate.isAfter(date) || (currentDate.isEqual(date) && !currentDateWithDay.isAfter(LocalDate.now()))) {
                    dailyRevenueMap.putIfAbsent(day, 0.0f);
                }
            }

            Map<Integer, Float> sortedDailyRevenueMap = dailyRevenueMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            List<String> daysOfMonth = sortedDailyRevenueMap.keySet().stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());

            List<Float> revenueValues = new ArrayList<>(sortedDailyRevenueMap.values());
            Map<String, Object> result = new HashMap<>();
            result.put("label", daysOfMonth);
            result.put("data", revenueValues);
            log.info("----- Get Report Revenue Each Day By Month End ------");
            return ResponseUtils.success(result, "Hiển thị doanh thu từng ngày theo tháng thành công");
        }catch (Exception e){
            log.error("getReportRevenueEachDayByMonth_isFail" +e.getMessage());
            return ResponseUtils.error("Hiển thị doanh thu từng ngày theo tháng thất bại");
        }
    }


    public ResponseDTO getReportRevenueDayOfWeekByMonthOrYear(String time, boolean isSearchByMonth) {
        log.info("----- Get Report Revenue DayOfWeek By Month Or Year Start ------");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(time, formatter);

            List<ReportRevenue> reportRevenues = isSearchByMonth ? reportRevenueRepository.findAllByMonth(date.getMonthValue(), date.getYear()) : reportRevenueRepository.findAllByYear(date.getYear());

            DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEEE", new Locale("vi", "VN"));

            Map<String, Float> dayOfWeeksRevenueSum = new LinkedHashMap<>();
            DayOfWeek[] daysOfWeekArray = DayOfWeek.values();
            for (DayOfWeek dayOfWeek : daysOfWeekArray) {
                dayOfWeeksRevenueSum.put(dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("vi", "VN")), 0.0f);
            }

            dayOfWeeksRevenueSum.putAll(reportRevenues.stream()
                    .collect(Collectors.groupingBy(
                            report -> report.getCreatedDate().toLocalDateTime().format(dayOfWeekFormatter),
                            Collectors.summingDouble(report -> BigDecimal.valueOf(report.getRevenueValue()).doubleValue())
                    ))
                    .entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().floatValue()
                    )));

            List<String> daysOfWeek = new ArrayList<>(dayOfWeeksRevenueSum.keySet());
            List<Float> revenueValues = new ArrayList<>(dayOfWeeksRevenueSum.values());

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("label", daysOfWeek);
            result.put("data", revenueValues);
            log.info("----- Get Report Revenue DayOfWeek By Month Or Year End ------");
            return ResponseUtils.success(result, isSearchByMonth ? "Hiển thị doanh thu từng thứ theo tháng thành công" : "Hiển thị doanh thu từng thứ theo năm thành công");
        }catch (Exception e){
            log.error("getReportRevenueDayOfWeekByMonthOrYear_isFail");
            return ResponseUtils.error("getReportRevenueDayOfWeekByMonthOrYear_isFail");
        }
    }

    public ResponseDTO getReportRevenueMonthByYear(Integer year) {
        log.info("----- Get Report Revenue Month By Year Start ------");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
            List<ReportRevenue> reportRevenues = reportRevenueRepository.findAllByYear(year);

            LocalDate currentDate = LocalDate.now();
            Map<String, Float> monthsOfYearRevenueSum = new LinkedHashMap<>();

            for (int month = 1; month <= 12; month++) {
                if (year < currentDate.getYear() || (year == currentDate.getYear() && month <= currentDate.getMonthValue())) {
                    monthsOfYearRevenueSum.put(String.format("%02d", month), 0.0f);
                }
            }

            if (year == currentDate.getYear()) {
                reportRevenues.stream()
                        .collect(Collectors.groupingBy(
                                report -> report.getCreatedDate().toLocalDateTime().format(formatter),
                                Collectors.summingDouble(report -> BigDecimal.valueOf(report.getRevenueValue()).doubleValue())
                        ))
                        .forEach((key, value) -> monthsOfYearRevenueSum.put(key, value.floatValue()));

                for (int month = currentDate.getMonthValue() + 1; month <= 12; month++) {
                    monthsOfYearRevenueSum.remove(String.format("%02d", month));
                }
            }

            List<String> monthsOfYear = new ArrayList<>(monthsOfYearRevenueSum.keySet());
            List<Float> revenueValues = new ArrayList<>(monthsOfYearRevenueSum.values());

            Map<String, Object> result = new HashMap<>();
            result.put("label", monthsOfYear);
            result.put("data", revenueValues);
            log.info("----- Get Report Revenue Month By Year End ------");
            return ResponseUtils.success(result, "Hiển thị doanh thu từng tháng theo năm thành công");
        }catch (Exception e){
            log.error("getReportRevenueMonthByYear_isFail");
            return ResponseUtils.error( "Hiển thị doanh thu từng tháng theo năm thất bại");
        }
    }

    public ResponseDTO getReportRevenueByManyYears(Integer startYear) {
        log.info("----- Get Report Revenue By Many Year Start ------");
        try {
            List<ReportRevenue> reportRevenues = reportRevenueRepository.findAllByYearRange(startYear);

            Map<Integer, Double> yearlySumMap = reportRevenues.stream()
                    .collect(Collectors.groupingBy(
                            report -> report.getCreatedDate().toLocalDateTime().getYear(),
                            Collectors.summingDouble(report -> BigDecimal.valueOf(report.getRevenueValue()).doubleValue())
                    ));

            int currentYear = LocalDate.now().getYear();
            for (int year = startYear; year <= currentYear; year++) {
                yearlySumMap.putIfAbsent(year, 0.0);
            }

            List<Integer> years = new ArrayList<>(yearlySumMap.keySet());
            List<Double> revenueValues = new ArrayList<>(yearlySumMap.values());

            Map<String, Object> result = new HashMap<>();
            result.put("label", years);
            result.put("data", revenueValues);
            log.info("----- Get Report Revenue By Many Year End ------");
            return ResponseUtils.success(result, "Hiển thị doanh thu theo nhiều năm thành công");
        }catch (Exception e){
            log.error("getReportRevenueByManyYears_isFail");
            return ResponseUtils.error( "Hiển thị doanh thu theo nhiều năm thất bại");
        }
    }

    public void writeTopRoomClass(String roomId, ReservationDetail reservationDetail){
        log.info("----- Write Top Room Class Start ------");
        try {
            Timestamp logTime = new Timestamp(System.currentTimeMillis());
            RoomCategory  roomCategory = roomClassRepository.findByRoomCategoryIdAndStatusNot(roomId, Status.DELETE);

            List<Order> orders = orderRepository.findByReservationDetail_ReservationDetailId(reservationDetail.getReservationDetailId());
                float totalOrder = 0;
                for (Order order: orders){
                    if(order.getStatus().equals(Status.PAID)){
                       totalOrder += order.getTotalPay();
                    }
                }
            List<ControlPolicy> controlPolicies = controlPolicyRepository.findControlPolicyByReservationDetail_ReservationDetailId(reservationDetail.getReservationDetailId());
                float totalPolicy = 0;
                for (ControlPolicy cp: controlPolicies){
                    totalPolicy += cp.getValue();
                }
            Float result = reservationDetail.getPrice() + totalPolicy + totalOrder;

            ReportTopRoomClass reportTopRoomClass = reportTopRoomClassRepository.findByRoomClassIdAndCreateDate(roomCategory.getRoomCategoryId(), logTime);

                if (reportTopRoomClass != null) {
                    reportTopRoomClass.setRevenue(reportTopRoomClass.getRevenue() + result);
                    reportTopRoomClass.setNumberOfRental(reportTopRoomClass.getNumberOfRental() + 1L);
                    reportTopRoomClass.setUpdateDate(logTime);
                } else {
                    reportTopRoomClass = new ReportTopRoomClass();
                    reportTopRoomClass.setRoomClassId(roomCategory.getRoomCategoryId());
                    reportTopRoomClass.setRevenue(result);
                    reportTopRoomClass.setNumberOfRental(1L);
                    reportTopRoomClass.setCreateDate(logTime);
                    reportTopRoomClassRepository.save(reportTopRoomClass);
                }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("----- Write Top Room Class End ------");
    }

    public ResponseDTO getTopRoomClassByMonth(String datestring, boolean isMonth, boolean isTotalRevenues) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate date = LocalDate.parse(datestring, formatter);

            List<Object[]> reportTopRoomClass = reportTopRoomClassRepository.getTotalRentalAndRevenueByMonth(isMonth ? date.getMonthValue() : null, date.getYear());
            Map<String, Double> resultMap = new HashMap<>();

            for (Object[] rt : reportTopRoomClass) {
                String roomClassId = String.valueOf(rt[0]);
                String roomCategoryName = getRoomCategoryById(roomClassId).getRoomCategoryName();
                Long totalNumberOfRental = (Long) rt[1];
                Double totalRevenue = (Double) rt[2];

                resultMap.put(roomCategoryName, isTotalRevenues ? totalRevenue : (double) totalNumberOfRental);
            }
            Map<String, Double> sortedMap = resultMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            Map<String, Object> result = new HashMap<>();
            result.put("label", new ArrayList<>(sortedMap.keySet()));
            result.put("data", new ArrayList<>(sortedMap.values()));

            return ResponseUtils.success(result, "getTopRoomClassByMonth_is_successfully");
        } catch (Exception e) {
            log.error("getTopRoomClassByMonth_failed" + e.getMessage());
            return ResponseUtils.error("getTopRoomClassByMonth_failed");
        }
    }

    public ResponseDTO getTopRoomClassByQuarter(Integer year, String quarter, boolean isTotalRevenues) {
            log.info(year.toString());
            log.info(quarter.toString());
        try {
            int quarterValue = Integer.parseInt(quarter);
            int startMonth = (quarterValue - 1) * 3 + 1;
            int endMonth = quarterValue * 3;

            List<Object[]> reportTopRoomClass = reportTopRoomClassRepository.getTotalRentalAndRevenueByCustomQuarter(year, startMonth, endMonth);
            Map<String, Double> resultMap = new HashMap<>();

            for (Object[] rt : reportTopRoomClass) {
                String roomClassId = String.valueOf(rt[0]);
                String roomCategoryName = getRoomCategoryById(roomClassId).getRoomCategoryName();
                Long totalNumberOfRental = (Long) rt[1];
                Double totalRevenue = (Double) rt[2];

                resultMap.put(roomCategoryName, isTotalRevenues ? totalRevenue : (double) totalNumberOfRental);
            }

            Map<String, Double> sortedMap = resultMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            Map<String, Object> result = new HashMap<>();
            result.put("label", new ArrayList<>(sortedMap.keySet()));
            result.put("data", new ArrayList<>(sortedMap.values()));

            return ResponseUtils.success(result, "getTopRoomClassByQuarter_is_successfully");
        } catch (NumberFormatException e) {
            log.error("getTopRoomClassByQuarter_failed: Error parsing integer - " + e.getMessage());
            return ResponseUtils.error("getTopRoomClassByQuarter_failed: Error parsing integer");
        } catch (Exception e) {
            log.error("getTopRoomClassByQuarter_failed: " + e.getMessage());
            return ResponseUtils.error("getTopRoomClassByQuarter_failed");
        }
    }


    public RoomCategory getRoomCategoryById(String id) {
        return roomClassRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Class not found with id " + id));
    }
}
