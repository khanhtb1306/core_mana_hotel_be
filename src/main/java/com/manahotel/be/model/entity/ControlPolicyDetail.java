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
public class ControlPolicyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "control_policy_detail_id")
    private Long controlPolicyDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_detail_id", nullable = false)
    private ReservationDetail reservationDetail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "control_policy_id", nullable = false)
    private ControlPolicy controlPolicy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "policy_detail_id", nullable = false)
    private PolicyDetail policyDetail;

    @Column(name = "type_value", length = 250)
    private String typeValue;

    @Column(name = "value")
    private Float value;

    @Column(name = "discrepancy", length = 250)
    private String discrepancy;

    @Column(name = "note", length = 350)
    private String note;

}
