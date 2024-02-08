package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Race;

import java.util.Collection;

public interface RaceService {

    Collection<Race> getRacesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Race> getRaces();
    Race getRaceByID(Long id);
    Race getRaceByName(String name);
    Boolean deleteRaceByID(Long id);
    Race saveRace(Race race);
    Race updateRace(Long id, Race race);
}
