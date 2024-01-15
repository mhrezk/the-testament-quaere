package com.testament.veltahleon.services.entities.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Measurement;
import com.testament.veltahleon.repositories.dao.ifc.history.MeasurementDAO;
import com.testament.veltahleon.services.entities.dao.ifc.history.MeasurementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementDAO measurementDAO;

    @Override
    public Collection<Measurement> getMeasurements() {
        return measurementDAO.getMeasurements();
    }

    @Override
    public Measurement getMeasurementByID(Long id) {
        return measurementDAO.getMeasurementByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteMeasurementByID(Long id) {
        return measurementDAO.deleteMeasurementByID(id);
    }

    @Override
    @Transactional
    public Measurement saveMeasurement(Measurement measurement) {
        return measurementDAO.saveMeasurement(measurement);
    }

    @Override
    @Transactional
    public Measurement updateMeasurement(Measurement measurement) {
        return measurementDAO.updateMeasurement(measurement);
    }
}
