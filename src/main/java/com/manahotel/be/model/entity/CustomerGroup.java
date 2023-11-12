package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer_group")
public class CustomerGroup {
    @Id
    @Size(max = 45)
    @Column(name = "customer_group_id", nullable = false, length = 45)
    private String customerGroupId;

    @Size(max = 45)
    @Column(name = "customer_group_name", length = 45)
    private String customerGroupName;

    @Size(max = 45)
    @Column(name = "status", length = 45)
    private String status;

}