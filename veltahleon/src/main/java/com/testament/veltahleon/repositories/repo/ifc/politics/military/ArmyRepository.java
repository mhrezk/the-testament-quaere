package com.testament.veltahleon.repositories.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Army;

import java.util.Collection;

public interface ArmyRepository {

    Collection<Army> getArmies();
    Army getArmyByID(Long id);
    Boolean deleteArmyByID(Long id);
    Army saveArmy(Army army);
    Army updateArmy(Army army);
}
