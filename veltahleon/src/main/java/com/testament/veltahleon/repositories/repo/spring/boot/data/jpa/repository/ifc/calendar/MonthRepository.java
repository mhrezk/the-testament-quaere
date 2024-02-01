package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Month;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MonthRepository extends JpaRepository<Month, Long> {

    Month findByName(String name);
    Collection<Month> findByNumberOfDays(Integer numberOfDays);
    Collection<Month> findByLanguage_Name(String name);
}
