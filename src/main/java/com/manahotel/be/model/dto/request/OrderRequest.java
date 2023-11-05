package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.OrderDTO;
import com.manahotel.be.model.dto.OrderDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private OrderDTO orderDTO;
    private List<OrderDetailDTO> orderDetailDTOList;
}
