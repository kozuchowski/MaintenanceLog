package com.maintenecelog.maintenancelog.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
public class Mainteiner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String licenceNumber;

    @OneToMany(mappedBy="mainteiner")
    @ToString.Exclude
    private List<Machine> machines = new ArrayList<>();

    public Mainteiner(String name, String surname, String login, String password, String email, String licenceNumber) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.licenceNumber = licenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Mainteiner mainteiner = (Mainteiner) o;
        return id != null && Objects.equals(id, mainteiner.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
