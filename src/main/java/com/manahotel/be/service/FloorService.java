package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.common.util.ResponseUtils;
import com.manahotel.be.model.dto.response.ResponseDTO;
import com.manahotel.be.model.entity.*;
import com.manahotel.be.repository.FloorRepository;
import com.manahotel.be.repository.ReservationDetailCustomerRepository;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private ReservationDetailCustomerRepository reservationDetailCustomerRepository;

    public ResponseDTO getAllRoomByFloor() {
        List<Object[]> listFloor = floorRepository.findRoomByFloor();
        List<Map<String, Object>> result = new ArrayList<>();

        for(Object[] floor : listFloor) {
            Floor f = (Floor) floor[0];
            List<Room> listRooms = roomRepository.findByFloor(f);
            List<Map<String, Object>> roomListWithRDs = new ArrayList<>();
            for (Room room : listRooms) {
                List<ReservationDetail> listReservationDetails = reservationDetailRepository.findReservationDetailByRoomAndReservationDetailStatus(room, Status.ACTIVATE);

                List<Map<String, Object>> RDListWithRDCs = new ArrayList<>();
                for (ReservationDetail reservationDetail : listReservationDetails) {
                    List<ReservationDetailCustomer> listReservationDetailCustomers = reservationDetailCustomerRepository.findReservationDetailCustomerByReservationDetail(reservationDetail);

                    Map<String, Object> reservationDetailCustomerInfo = new HashMap<>();
                    reservationDetailCustomerInfo.put("reservationDetail", reservationDetail);
                    reservationDetailCustomerInfo.put("listReservationDetailCustomers", listReservationDetailCustomers.toArray());
                    RDListWithRDCs.add(reservationDetailCustomerInfo);
                }

                Map<String, Object> reservationDetailInfo = new HashMap<>();
                reservationDetailInfo.put("room", room);
                reservationDetailInfo.put("RDListWithRDCs", RDListWithRDCs.toArray());
                roomListWithRDs.add(reservationDetailInfo);
            }

            Map<String, Object> roomInfo = new HashMap<>();
            roomInfo.put("floor", floor);
            roomInfo.put("roomListWithRDs", roomListWithRDs.toArray());
            result.add(roomInfo);
        }
        return ResponseUtils.success(result, "Hiển thị danh sách phòng theo danh sách khu vực thành công");
    }
}
