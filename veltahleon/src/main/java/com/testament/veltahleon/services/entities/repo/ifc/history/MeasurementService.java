package com.testament.veltahleon.services.entities.repo.ifc.history;

import com.testament.veltahleon.model.entities.history.Measurement;

import java.util.Collection;

public interface MeasurementService {

    Collection<Measurement> getMeasurementsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Measurement> getMeasurements();
    Measurement getMeasurementByID(Long id);
    Measurement getMeasurementByName(String name);
    Boolean deleteMeasurementByID(Long id);
    Measurement saveMeasurement(Measurement measurement);
    Measurement updateMeasurement(Long id, Measurement measurement);
}
