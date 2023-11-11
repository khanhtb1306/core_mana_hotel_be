package com.manahotel.be.model.dto;

import com.manahotel.be.model.entity.ReservationDetail;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class OrderDTO {
    private String orderId;
    private long reservationDetailId;
    private Float totalPay;
    private String status;
    private Long createdById;
    private Instant createdDate;

}
