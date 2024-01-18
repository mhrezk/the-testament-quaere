package com.testament.veltahleon.repositories.repo.custom.repository.ifc.history;

import com.testament.veltahleon.model.entities.history.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {}
