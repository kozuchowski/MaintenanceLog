package com.maintenecelog.maintenancelog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
public class CompanyOwner extends Owner {
    private String companyName;
}
