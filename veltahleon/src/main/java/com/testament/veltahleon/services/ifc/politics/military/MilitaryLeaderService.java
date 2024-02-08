package com.testament.veltahleon.services.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;

import java.util.Collection;

public interface MilitaryLeaderService {

    Collection<MilitaryLeader> getMilitaryLeadersWithPagination(int pageNumber, int numberOfRecords);
    Collection<MilitaryLeader> getMilitaryLeaders();
    Collection<MilitaryLeader> getMilitaryLeaderByNationName(String name);
    MilitaryLeader getMilitaryLeaderByID(Long id);
    MilitaryLeader getMilitaryLeaderByName(String name);
    Boolean deleteMilitaryLeaderByID(Long id);
    MilitaryLeader saveMilitaryLeader(MilitaryLeader militaryLeader);
    MilitaryLeader updateMilitaryLeader(Long id, MilitaryLeader militaryLeader);
}
