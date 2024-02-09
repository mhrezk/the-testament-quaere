package com.testament.veltahleon.repositories.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface YearRepository extends JpaRepository<Year, Long> {

    Collection<Year> findByName(String name);
    Year findByDay_DayNumberAndMonth_MonthNumberAndEpoch_YearNumber(Integer day, Integer month, Integer year);
}
