package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.InvoiceDTO;
import com.manahotel.be.model.dto.response.ReservationDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceRequest {
    private List<ReservationDetailDTO> reservationDetailDTO;
    private InvoiceDTO invoiceDTO;
}