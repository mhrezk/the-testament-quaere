package com.testament.veltahleon.repositories.dao.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.SquadLeader;

import java.util.Collection;

public interface SquadLeaderDAO {

    Collection<SquadLeader> getSquadLeaders();
    SquadLeader getSquadLeaderByID(Long id);
    Boolean deleteSquadLeaderByID(Long id);
    SquadLeader saveSquadLeader(SquadLeader squadLeader);
    SquadLeader updateSquadLeader(SquadLeader squadLeader);
}
