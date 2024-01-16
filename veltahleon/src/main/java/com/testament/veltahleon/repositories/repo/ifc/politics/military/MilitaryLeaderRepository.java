package com.testament.veltahleon.repositories.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;

import java.util.Collection;

public interface MilitaryLeaderRepository {

    Collection<MilitaryLeader> getMilitaryLeaders();
    MilitaryLeader getMilitaryLeaderByID(Long id);
    Boolean deleteMilitaryLeaderByID(Long id);
    MilitaryLeader saveMilitaryLeader(MilitaryLeader militaryLeader);
    MilitaryLeader updateMilitaryLeader(MilitaryLeader militaryLeader);
}
