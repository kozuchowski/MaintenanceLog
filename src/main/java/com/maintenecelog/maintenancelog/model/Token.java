package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@NoArgsConstructor
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(unique = true)
    private String token;
    private LocalDateTime created;
    private LocalDateTime expiring;

    @ManyToOne
    private Mainteiner mainteiner;

    public Token(String token, Mainteiner mainteiner) {
        this.token = token;
        this.created = LocalDateTime.now();
        this.expiring = LocalDateTime.now().plusDays(7);
        this.mainteiner = mainteiner;
    }

}
