package com.testament.veltahleon.services.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.ArmyLeader;

import java.util.Collection;

public interface ArmyLeaderService {

    Collection<ArmyLeader> getArmyLeadersWithPagination(int pageNumber, int numberOfRecords);
    Collection<ArmyLeader> getArmyLeaders();
    ArmyLeader getArmyLeaderByID(Long id);
    ArmyLeader getArmyLeaderByName(String name);
    Boolean deleteArmyLeaderByID(Long id);
    ArmyLeader saveArmyLeader(ArmyLeader armyLeader);
    ArmyLeader updateArmyLeader(Long id, ArmyLeader armyLeader);
}
