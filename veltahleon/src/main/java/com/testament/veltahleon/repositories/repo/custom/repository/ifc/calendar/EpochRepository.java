package com.testament.veltahleon.repositories.repo.custom.repository.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpochRepository extends JpaRepository<Epoch, Long> {
}
