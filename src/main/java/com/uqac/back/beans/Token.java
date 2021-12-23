package com.uqac.back.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@ToString
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idToken", nullable = false)
    private Integer id;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "DateExpirationToken", nullable = false)
    private Instant dateExpirationToken;

}