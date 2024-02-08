package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.model.entities.history.Measurement;
import com.testament.veltahleon.repositories.history.MeasurementRepository;
import com.testament.veltahleon.services.ifc.history.MeasurementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public Collection<Measurement> getMeasurementsWithPagination(int pageNumber, int numberOfRecords) {
        return measurementRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    @Override
    public Measurement getMeasurementByID(Long id) {
        return measurementRepository.findById(id).orElseThrow();
    }

    @Override
    public Measurement getMeasurementByName(String name) {
        return measurementRepository.findByName(name);
    }

    @Override
    public Boolean deleteMeasurementByID(Long id) {
        measurementRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Measurement saveMeasurement(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    @Override
    public Measurement updateMeasurement(Long id, Measurement measurement) {
        Measurement newMeasurement = measurementRepository.findById(id).orElseThrow();

        if(measurement.getName() != null && newMeasurement.getName() != measurement.getName()) {
            newMeasurement.setName(measurement.getName());
        }

        if(measurement.getAbbreviation() != null && newMeasurement.getAbbreviation() != measurement.getAbbreviation()) {
            newMeasurement.setAbbreviation(measurement.getAbbreviation());
        }

        if(measurement.getDescription() != null && newMeasurement.getDescription() != measurement.getDescription()) {
            newMeasurement.setDescription(measurement.getDescription());
        }

        return measurementRepository.save(newMeasurement);
    }
}
