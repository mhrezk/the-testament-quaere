package com.testament.veltahleon.repositories.dao.ifc.history;

import com.testament.veltahleon.model.entities.history.Race;

import java.util.Collection;

public interface RaceDAO {

    Collection<Race> getRaces();
    Race getRaceByID(Long id);
    Boolean deleteRaceByID(Long id);
    Race saveRace(Race Race);
    Race updateRace(Race Race);
}
