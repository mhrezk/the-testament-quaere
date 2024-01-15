package com.testament.veltahleon.repositories.dao.ifc.history;

import com.testament.veltahleon.model.entities.history.Measurement;

import java.util.Collection;

public interface MeasurementDAO {

    Collection<Measurement> getMeasurements();
    Measurement getMeasurementByID(Long id);
    Boolean deleteMeasurementByID(Long id);
    Measurement saveMeasurement(Measurement measurement);
    Measurement updateMeasurement(Measurement measurement);
}
