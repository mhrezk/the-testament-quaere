package com.testament.veltahleon.repositories.repo.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DayRepository extends JpaRepository<Day, Long> {}
