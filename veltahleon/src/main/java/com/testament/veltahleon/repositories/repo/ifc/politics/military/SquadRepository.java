package com.testament.veltahleon.repositories.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;

import java.util.Collection;

public interface SquadRepository {

    Collection<Squad> getSquads();
    Squad getSquadByID(Long id);
    Boolean deleteSquadByID(Long id);
    Squad saveSquad(Squad squad);
    Squad updateSquad(Squad squad);
}
