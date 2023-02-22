package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class Token {
    private String token;
    private LocalDateTime created;
    private LocalDateTime expiring;
    private Long mainteinerId;

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", created=" + created +
                ", expiring=" + expiring +
                ", mainteinerId=" + mainteinerId +
                '}';
    }

    public Token(String token, Long mainteinerId) {
        this.token = token;
        this.created = LocalDateTime.now();
        this.expiring = LocalDateTime.now().plusDays(7);
        this.mainteinerId = mainteinerId;
    }


}
