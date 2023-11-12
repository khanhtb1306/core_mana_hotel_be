package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "control_policy", indexes = {
        @Index(name = "pk_cp_r_idx", columnList = "reservation_id"),
        @Index(name = "pk_cp_p_idx", columnList = "policy_id")
})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlPolicy {
    @Id
    @Column(name = "control_policy_id", length = 50)
    private String controlPolicyId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "note", length = 350)
    private String note;
}
