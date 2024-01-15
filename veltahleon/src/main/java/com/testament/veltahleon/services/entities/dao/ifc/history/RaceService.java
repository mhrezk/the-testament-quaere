package com.testament.veltahleon.services.entities.dao.ifc.history;

import com.testament.veltahleon.model.entities.history.Race;

import java.util.Collection;

public interface RaceService {

    Collection<Race> getRaces();
    Race getRaceByID(Long id);
    Boolean deleteRaceByID(Long id);
    Race saveRace(Race race);
    Race updateRace(Race race);
}
