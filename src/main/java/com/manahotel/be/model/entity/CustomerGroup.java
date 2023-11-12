package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer_group")
public class CustomerGroup {
    @Id
    @Column(name = "customer_group_id", nullable = false, length = 45)
    private String customerGroupId;

    @Column(name = "customer_group_name", length = 45)
    private String customerGroupName;

    @Column(name = "status", length = 45)
    private String status;

}