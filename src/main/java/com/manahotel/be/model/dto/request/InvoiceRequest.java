package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.InvoiceDTO;
import com.manahotel.be.model.dto.ReservationDetailDTO;
import lombok.Data;

@Data
public class InvoiceRequest {
    private ReservationDetailDTO reservationDetailDTO;
    private InvoiceDTO invoiceDTO;
    private boolean partial;
}