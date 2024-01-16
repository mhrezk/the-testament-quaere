package com.testament.veltahleon.repositories.repo.ifc.history;

import com.testament.veltahleon.model.entities.history.Race;

import java.util.Collection;

public interface RaceRepository {

    Collection<Race> getRaces();
    Race getRaceByID(Long id);
    Boolean deleteRaceByID(Long id);
    Race saveRace(Race Race);
    Race updateRace(Race Race);
}
