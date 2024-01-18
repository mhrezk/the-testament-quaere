package com.testament.veltahleon.services.entities.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Army;

import java.util.Collection;

public interface ArmyService {
    Collection<Army> getArmies();
    Army getArmyByID(Long id);
    Boolean deleteArmyByID(Long id);
    Army saveArmy(Army army);
    Army updateArmy(Army army);

}
