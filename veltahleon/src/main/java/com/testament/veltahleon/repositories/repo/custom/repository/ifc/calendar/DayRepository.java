package com.testament.veltahleon.repositories.repo.custom.repository.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface DayRepository<T extends Day> {

    Collection<Day> getDays();
    Collection<Day> getDays(int pageNumber, int numberOfRecords);
    Day getDayByID(Long id);
    Boolean deleteDayByID(Long id);
    Day saveDay(Day day);
    Day updateDay(Day day);
}
