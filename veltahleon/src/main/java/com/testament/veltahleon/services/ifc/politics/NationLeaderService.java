package com.testament.veltahleon.services.ifc.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;

import java.util.Collection;

public interface NationLeaderService {

    Collection<NationLeader> getNationLeadersWithPagination(int pageNumber, int numberOfRecords);
    Collection<NationLeader> getNationLeaders();
    NationLeader getNationLeaderByID(Long id);
    NationLeader getNationLeaderByName(String name);
    Boolean deleteNationLeaderByID(Long id);
    NationLeader saveNationLeader(NationLeader nationLeader);
    NationLeader updateNationLeader(Long id, NationLeader nationLeader);
}
