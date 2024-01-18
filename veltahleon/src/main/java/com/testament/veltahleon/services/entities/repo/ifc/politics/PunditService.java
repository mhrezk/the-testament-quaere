package com.testament.veltahleon.services.entities.repo.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Pundit;

import java.util.Collection;

public interface PunditService {

    Collection<Pundit> getPundits();
    Pundit getPunditByID(Long id);
    Boolean deletePunditByID(Long id);
    Pundit savePundit(Pundit pundit);
    Pundit updatePundit(Pundit pundit);
}
