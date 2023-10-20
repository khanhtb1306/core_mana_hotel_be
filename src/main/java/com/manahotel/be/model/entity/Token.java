package com.manahotel.be.model.entity;

import com.manahotel.be.model.entity.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
public class Token {
    private static final int EXPIRATION_TIME = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Token(String token, Staff staff) {
        super();
        this.token = token;
        this.staff = staff;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public Token(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public Date getTokenExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }

}
