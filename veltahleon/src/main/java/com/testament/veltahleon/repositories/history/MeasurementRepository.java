package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Measurement;
import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Measurement findByName(String name);
}
