package com.testament.veltahleon.services.entities.dao.ifc.divination;

import com.testament.veltahleon.model.entities.divination.Tarot;

import java.util.Collection;

public interface TarotService {

    Collection<Tarot> getTarots();
    Tarot getTarotByID(Long id);
    Boolean deleteTarotByID(Long id);
    Tarot saveTarot(Tarot tarot);
    Tarot updateTarot(Tarot tarot);
}
