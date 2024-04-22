package com.testament.veltahleon.repositories.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.calendar.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface YearRepository extends JpaRepository<Year, Long> {

    Collection<Year> findByName(String name);
    Collection<Year> findByEpoch_YearNumber(int year);
    Year findByDay_DayNumberAndMonth_MonthNumberAndEpoch_YearNumber(int day, int month, int year);
}
