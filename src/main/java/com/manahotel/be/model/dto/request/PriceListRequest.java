package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.response.PriceListDTO;
import com.manahotel.be.model.dto.response.PriceListDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class PriceListRequest {
    private PriceListDTO priceListDTO;
    private List<PriceListDetailDTO> priceListDetailDTO;
}