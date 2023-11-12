package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "customer")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "customer_name", length = 250)
    private String customerName;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "dob")
    private Timestamp dob;

    @Column(name = "email", length = 350)
    private String email;

    @Column(name = "address", length = 350)
    private String address;

    @Column(name = "identity", length = 350)
    private String identity;

    @Column(name = "nationality", length = 350)
    private String nationality;

    @Column(name = "tax_code", length = 350)
    private String taxCode;

    @Column(name = "gender")
    private Boolean gender;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @NotNull
    @ManyToOne( optional = false)
    @JoinColumn(name = "customer_group_id", nullable = false)
    private CustomerGroup customerGroup;

    @Column(name = "point")
    private Float point;

}