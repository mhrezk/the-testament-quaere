package com.testament.veltahleon.repositories.repo.ifc.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;

import java.util.Collection;

public interface NationLeaderRepository {

    Collection<NationLeader> getNationLeaders();
    NationLeader getNationLeaderByID(Long id);
    Boolean deleteNationLeaderByID(Long id);
    NationLeader saveNationLeader(NationLeader nationLeader);
    NationLeader updateNationLeader(NationLeader nationLeader);
}
