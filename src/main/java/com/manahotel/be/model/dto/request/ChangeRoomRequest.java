package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import lombok.Data;

@Data
public class ChangeRoomRequest {
    private String reservationId;
    private String roomId;
    private ReservationDetailDTO reservationDetailDTO;
}
