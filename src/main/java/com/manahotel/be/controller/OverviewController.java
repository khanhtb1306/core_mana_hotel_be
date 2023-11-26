package com.manahotel.be.controller;

import com.manahotel.be.model.dto.ResponseDTO;
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

    @GetMapping("/report_room_capacity_current_month")
    public ResponseDTO getReportRoomCapacityCurrentMonth() {
        return overviewService.getReportRoomCapacityCurrentMonth();
    }

    @GetMapping("/report_room_capacity_last_month")
    public ResponseDTO getReportRoomCapacityLastMonth() {
        return overviewService.getReportRoomCapacityLastMonth();
    }

    @GetMapping("/report_revenue_each_day_by_month")
    public ResponseDTO getReportRevenueEachDayByMonth(Integer month) {
        return overviewService.getReportRevenueEachDayByMonth(month);
    }
}
