package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.SubRace;

import java.util.Collection;

public interface SubRaceService {
    Collection<SubRace> getSubRacesWithPagination(int pageNumber, int pageSize);
    Collection<SubRace> getSubRacesWithPaginationByRaceName(String name, int pageNumber, int pageSize);
    Collection<SubRace> getSubRaces();
    Collection<SubRace> getSubRacesByRaceName(String name);
    Boolean deleteSubRaceByID(Long id);
    Boolean doesSubRaceNameExist(String name);
    SubRace getSubRaceByID(Long id);
    SubRace getSubRaceByName(String name);
    SubRace saveSubRace(SubRace subRace, String raceName);
    SubRace updateSubRace(Long id, SubRace subRace);
    SubRace modifySubRace(Long id, SubRace subRace);
    Long countSubRaces();
    Long countSubRaceByRaceName(String name);
}
