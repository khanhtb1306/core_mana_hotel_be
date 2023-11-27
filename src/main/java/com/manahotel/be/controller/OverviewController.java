package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.service.OverviewService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

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

    @GetMapping("/report_room_capacity_with_day_of_week_by_month")
    public ResponseDTO getReportRoomCapacityWithDayOfWeekByMonth(String date) {
        return overviewService.getReportRoomCapacityWithDayOfWeekByMonth(date);
    }

    @GetMapping("/report_revenue_each_day_by_month")
    public ResponseDTO getReportRevenueEachDayByMonth(Integer month) {
        return overviewService.getReportRevenueEachDayByMonth(month);
    }
}
