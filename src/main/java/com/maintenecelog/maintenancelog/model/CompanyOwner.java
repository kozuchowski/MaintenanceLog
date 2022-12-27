package com.maintenecelog.maintenancelog.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@RequiredArgsConstructor
public class CompanyOwner extends Owner {
    private String companyName;
}
