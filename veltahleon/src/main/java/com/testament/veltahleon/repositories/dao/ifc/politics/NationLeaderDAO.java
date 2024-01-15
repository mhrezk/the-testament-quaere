package com.testament.veltahleon.repositories.dao.ifc.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;

import java.util.Collection;

public interface NationLeaderDAO {

    Collection<NationLeader> getNationLeaders();
    NationLeader getNationLeaderByID(Long id);
    Boolean deleteNationLeaderByID(Long id);
    NationLeader saveNationLeader(NationLeader nationLeader);
    NationLeader updateNationLeader(NationLeader nationLeader);
}
