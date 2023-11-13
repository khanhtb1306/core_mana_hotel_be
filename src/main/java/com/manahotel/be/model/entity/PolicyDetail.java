package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "policy_detail")
public class PolicyDetail {
    @Id
    @Column(name = "policy_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_category_id", nullable = false)
    private RoomCategory roomCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_group_id", nullable = false)
    private CustomerGroup customerGroup;

    @Column(name = "type", length = 250)
    private String type;

    @Column(name = "unit", length = 250)
    private String unit;

    @Column(name = "limit_value")
    private Long limitValue;

    @Column(name = "type_value", length = 250)
    private String typeValue;

    @Column(name = "other", length = 250)
    private String other;

    @Column(name = "requirement", length = 250)
    private String requirement;

    @Column(name = "policy_value")
    private Float policyValue;

    @Column(name = "note", length = 350)
    private String note;

    @Column(name = "auto_add_to_invoice")
    private Boolean autoAddToInvoice;

    @Column(name = "status")
    private Long status;
}
