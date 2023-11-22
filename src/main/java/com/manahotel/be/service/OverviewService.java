package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.ResponseDTO;
import com.manahotel.be.model.entity.RecentActivity;
import com.manahotel.be.model.entity.ReportRoomCapacity;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.repository.RecentActivityRepository;
import com.manahotel.be.repository.ReportRoomCapacityRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @Scheduled(cron = "0 59 23 * * ?")  // Chạy vào 23:59 hàng ngày
    public void checkRoomCapacityDaily(){
        try {
            List<Room> listRoom = roomRepository.findByStatus(Status.ACTIVATE);
            int totalRooms = listRoom.size();
            int totalTimeUseOfRoomsMax = totalRooms * 24*60*60*1000;
            LocalDate today = LocalDate.now();
            int totalTimeUseToday = 0;
            for(Room room : listRoom){
                List<ReservationDetail> rdList = reservationDetailRepository.checkRoomCapacityDaily(room.getRoomId());
                int TotalTimestamp = 0;
                for(ReservationDetail rd:  rdList ){
                    int timestamp = 0;
                    LocalDate checkInDate = rd.getCheckInEstimate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if(rd.getStatus().equals(Status.CHECK_IN)){
                        if (!checkInDate.equals(today)) {
                            timestamp = 24 * 60 * 60 * 1000; // 24 hours
                        }else {
                            long timestampMillis = (24 * 60 * 60 * 1000) - rd.getCheckInEstimate().getTime();
                            timestamp = (int) timestampMillis;
                        }
                    }else if(rd.getStatus().equals(Status.CHECK_OUT)){
                        long timestampMillis;
                        if (!checkInDate.equals(today)) {
                            timestampMillis = rd.getCheckOutActual().getTime();
                        }else {
                            timestampMillis = rd.getCheckOutActual().getTime() - rd.getCheckInEstimate().getTime();
                        }
                        timestamp = (int) timestampMillis;
                    }
                    TotalTimestamp += timestamp;
                }
                totalTimeUseToday += TotalTimestamp;
            }
            float RoomCapacityToday = totalTimeUseToday/totalTimeUseOfRoomsMax;

            ReportRoomCapacity reportRoomCapacity = new ReportRoomCapacity();
            reportRoomCapacity.setRoomCapacityValue(RoomCapacityToday);
            reportRoomCapacity.setCreateDate(new Timestamp(System.currentTimeMillis()));
            reportRoomCapacityRepository.save(reportRoomCapacity);
        }catch (Exception e){
            log.error(e.getMessage());
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

    public boolean writeRecentActivity(String staff_name, String action, float value, Timestamp create_time){
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
            return false;
        }
        return true;
    }
}
