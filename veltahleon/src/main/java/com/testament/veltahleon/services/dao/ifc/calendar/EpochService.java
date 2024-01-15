package com.testament.veltahleon.services.dao.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;

import java.util.Collection;

public interface EpochService {

    Collection<Epoch> getEpochs();
    Epoch getEpochByID(Long id);
    Boolean deleteEpochByID(Long id);
    Epoch saveEpoch(Epoch epoch);
    Epoch updateEpoch(Epoch epoch);
}
