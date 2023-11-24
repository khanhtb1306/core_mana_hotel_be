package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.ReservationDetailCustomerDTO;
import lombok.Data;

@Data
public class ReservationDetailCustomerRequest {
    private ReservationDetailCustomerDTO reservationDetailCustomerDTO;
    private boolean isAdult;
    private boolean check;
}
