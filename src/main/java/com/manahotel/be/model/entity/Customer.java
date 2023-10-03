package com.manahotel.be.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_group")
    private String customerGroup;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "dob")
    private Timestamp dob;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "identity")
    private String identity;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "gender")
    private boolean gender;
}
