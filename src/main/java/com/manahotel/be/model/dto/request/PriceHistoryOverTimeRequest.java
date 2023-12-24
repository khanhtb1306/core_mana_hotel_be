package com.manahotel.be.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PriceHistoryOverTimeRequest {
    private List<ListTimePrice> timePrices;
    private Long reservationDetailId;
}
