package com.testament.veltahleon.services.entities.repo.ifc.divination;

import com.testament.veltahleon.model.entities.divination.Tarot;

import java.util.Collection;

public interface TarotService {

    Collection<Tarot> getTarotsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Tarot> getTarots();
    Tarot getTarotByID(Long id);
    Boolean deleteTarotByID(Long id);
    Tarot saveTarot(Tarot tarot);
    Tarot updateTarot(Long id, Tarot tarot);
}
