package com.testament.veltahleon.services.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;

import java.util.Collection;

public interface SquadService {

    Collection<Squad> getSquadsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Squad> getSquads();
    Squad getSquadByID(Long id);
    Squad getSquadBySquadLeader(String name);
    Boolean deleteSquadByID(Long id);
    Squad saveSquad(Squad squad);
    Squad updateSquad(Long id, Squad squad);
}
