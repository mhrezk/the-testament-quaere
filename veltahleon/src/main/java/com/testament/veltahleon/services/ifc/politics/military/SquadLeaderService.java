package com.testament.veltahleon.services.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.SquadLeader;

import java.util.Collection;

public interface SquadLeaderService {

    Collection<SquadLeader> getSquadLeadersWithPagination(int pageNumber, int numberOfRecords);
    Collection<SquadLeader> getSquadLeaders();
    SquadLeader getSquadLeaderByID(Long id);
    SquadLeader getSquadLeaderByName(String name);
    Boolean deleteSquadLeaderByID(Long id);
    SquadLeader saveSquadLeader(SquadLeader squadLeader);
    SquadLeader updateSquadLeader(Long id, SquadLeader squadLeader);
}
