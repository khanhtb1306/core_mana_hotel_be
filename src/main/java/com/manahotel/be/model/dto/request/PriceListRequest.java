package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.PriceListDTO;
import com.manahotel.be.model.dto.PriceListDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class PriceListRequest {
    private PriceListDTO priceListDTO;
    private List<PriceListDetailDTO> priceListDetailDTO;
}