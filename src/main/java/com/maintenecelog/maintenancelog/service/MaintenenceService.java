package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.model.Machine;
import com.maintenecelog.maintenancelog.model.Maintenence;

import java.time.LocalDate;
import java.util.List;

public interface MaintenenceService {
    List<Maintenence> findAllByMachine(Machine machine);

    Maintenence findByDate(LocalDate exDate);

    void deleteByDate(LocalDate date, Machine machine);
}
