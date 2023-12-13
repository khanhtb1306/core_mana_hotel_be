package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.InvoiceDTO;
import com.manahotel.be.model.dto.response.OrderDetailDTO;
import lombok.Data;

import java.util.List;
@Data
public class InvoicePurchaseRequest {
    private InvoiceDTO invoiceDTO;
    private List<OrderDetailDTO> orderDetailDTOList;
}
