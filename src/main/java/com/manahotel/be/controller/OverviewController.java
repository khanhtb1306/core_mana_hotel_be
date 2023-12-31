package com.manahotel.be.controller;

import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.service.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/overview")
public class OverviewController {

    @Autowired
    private OverviewService overviewService;

    @GetMapping("/recent_activity")
    public ResponseDTO getAllRecentActivity() {
        return overviewService.getRecentActivity();
    }

    @GetMapping("/room_capacity")
    public ResponseDTO getCountRoomCapacity() {
        return overviewService.getRoomCapacity();
    }

    @GetMapping("/report_room_capacity_by_month")
    public ResponseDTO getReportRoomCapacityCurrentMonth(String date) {
        return overviewService.getReportRoomCapacityByMonth(date);
    }

    @GetMapping("/report_room_capacity_by_year")
    public ResponseDTO getReportRoomCapacityByYear(Integer year) {
        return overviewService.getReportRoomCapacityByYear(year);
    }
    @GetMapping("/report_room_capacity_by_many_year")
    public ResponseDTO getReportRoomCapacityByManyYear(Integer year) {
        return overviewService.getReportRoomCapacityByManyYear(year);
    }

    @GetMapping("/report_room_capacity_with_day_of_week_by_month")
    public ResponseDTO getReportRoomCapacityWithDayOfWeekByMonth(String date) {
        return overviewService.getReportRoomCapacityWithDayOfWeekByMonth(date, true);
    }
    @GetMapping("/report_room_capacity_with_day_of_week_by_year")
    public ResponseDTO getReportRoomCapacityWithDayOfWeekByYear(String date) {
        return overviewService.getReportRoomCapacityWithDayOfWeekByMonth(date, false);
    }

    @GetMapping("/report_revenue_each_day_by_month")
    public ResponseDTO getReportRevenueEachDayByMonth(String date) {
        return overviewService.getReportRevenueEachDayByMonth(date);
    }

    @GetMapping("/report_revenue_day_of_week_by_month")
    public ResponseDTO getReportRevenueDayOfWeekByMonth(String date) {
        return overviewService.getReportRevenueDayOfWeekByMonthOrYear(date, true);
    }

    @GetMapping("/report_revenue_day_of_week_by_year")
    public ResponseDTO getReportRevenueDayOfWeekByYear(String date) {
        return overviewService.getReportRevenueDayOfWeekByMonthOrYear(date, false);
    }

    @GetMapping("/report_revenue_month_by_year")
    public ResponseDTO getReportRevenueMonthByYear(Integer year) {
        return overviewService.getReportRevenueMonthByYear(year);
    }

    @GetMapping("/report_revenue_by_many_years")
    public ResponseDTO getReportRevenueByManyYears(Integer startYear) {
        return overviewService.getReportRevenueByManyYears(startYear);
    }
    @GetMapping("/get_top_room_class_by_month_or_year")
    public ResponseDTO getTopRoomClassByMonth(String datestring, boolean isMonth, boolean isTotalRevenues) {
        return overviewService.getTopRoomClassByMonth(datestring, isMonth, isTotalRevenues);
    }
    @GetMapping("/get_top_room_class_by_quarter")
    public ResponseDTO getTopRoomClassByQuarter(Integer year, String quarter, boolean isTotalRevenues) {
        return overviewService.getTopRoomClassByQuarter(year, quarter, isTotalRevenues);
    }
}
