package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.ReservationDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class ReservationDetailRequest {
    private ReservationDetailDTO reservationDetailDTO;
    private List<String> customerIds;
}
