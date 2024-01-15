package com.testament.veltahleon.repositories.dao.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.ArmyLeader;

import java.util.Collection;

public interface ArmyLeaderDAO {

    Collection<ArmyLeader> getArmyLeaders();
    ArmyLeader getArmyLeaderByID(Long id);
    Boolean deleteArmyLeaderByID(Long id);
    ArmyLeader saveArmyLeader(ArmyLeader armyLeader);
    ArmyLeader updateArmyLeader(ArmyLeader armyLeader);
}
