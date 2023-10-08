package com.manahotel.be.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Table(name = "staff", indexes = {
        @Index(name = "pk_s_r_idx", columnList = "role_id")
})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @Column(name = "staff_name", length = 250)
    private String staffName;

    @Column(name = "username", length = 50)
    private String userName;

    @Column(name = "password", length = 150)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "status")
    private Long status;

    @Column(name = "dob")
    private Timestamp dob;

    @Column(name = "address", length = 350)
    private String address;

    @Column(name = "email", length = 350)
    private String email;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "identity", length = 350)
    private String identity;

    @Column(name = "tax_code", length = 350)
    private String taxCode;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "updated_by_id")
    private Long updatedById;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;
}