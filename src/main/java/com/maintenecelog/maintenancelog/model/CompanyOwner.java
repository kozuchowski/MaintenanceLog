package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class CompanyOwner extends Owner {
    private String companyName;

    public CompanyOwner(String phoneNumber, String companyName) {
        super(phoneNumber);
        this.companyName = companyName;
    }
}
