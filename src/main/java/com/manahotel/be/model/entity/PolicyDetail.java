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
    private Long policyDetailId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policyId;

    @Column(name = "room_category_id")
    private String roomCategoryId;

    @Column(name = "customer_group")
    private String customerGroup;

    @Column(name = "type")
    private String type;

    @Column(name = "unit")
    private String unit;

    @Column(name = "from")
    private Long from;

    @Column(name = "to")
    private Long to;

    @Column(name = "type_value")
    private String typeValue;

    @Column(name = "other")
    private String other;

    @Column(name = "condition")
    private String condition;

    @Column(name = "value")
    private Float value;

    @Column(name = "note")
    private String note;

    @Column(name = "auto_add_to_invoice")
    private Boolean autoAddToInvoice;

    @Column(name = "status")
    private Long status;
}
