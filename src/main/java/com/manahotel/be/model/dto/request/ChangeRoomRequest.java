package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.ReservationDTO;
import com.manahotel.be.model.dto.ReservationDetailDTO;
import lombok.Data;

@Data
public class ChangeRoomRequest {
    private String reservationId;
    private String roomId;
    private ReservationDTO reservationDTO;
    private ReservationDetailDTO reservationDetailDTO;
}
