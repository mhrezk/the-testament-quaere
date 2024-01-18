package com.testament.veltahleon.repositories.repo.custom.repository.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<Month, Long> {
}