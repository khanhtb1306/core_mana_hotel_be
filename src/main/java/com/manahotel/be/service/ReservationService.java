package com.manahotel.be.service;

import com.manahotel.be.model.entity.Reservation;
import com.manahotel.be.model.entity.ReservationDetail;
import com.manahotel.be.repository.ReservationDetailRepository;
import com.manahotel.be.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ReservationDetailRepository repository2;

    public ResponseEntity<List<Map<String, Object>>> getAllReservationWithRooms() {
        List<Object[]> listReservations = repository.findReservationsWithRooms();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] reservation : listReservations) {
            Reservation r = (Reservation) reservation[0];
            List<ReservationDetail> listReservationDetails = repository2.findReservationDetailByReservation(r);
            Map<String, Object> detailInfo = new HashMap<>();
            detailInfo.put("reservation", r);
            detailInfo.put("listReservationDetails", listReservationDetails.toArray());
            result.add(detailInfo);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
