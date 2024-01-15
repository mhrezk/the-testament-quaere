package com.testament.veltahleon.repositories.dao.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Pundit;

import java.util.Collection;

public interface PunditDAO {

    Collection<Pundit> getPundits();
    Pundit getPunditByID(Long id);
    Boolean deletePunditByID(Long id);
    Pundit savePundit(Pundit pundit);
    Pundit updatePundit(Pundit pundit);
}
