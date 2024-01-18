package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.divination;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.divination.Constellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
}
