package com.testament.veltahleon.services.entities.repo.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;

import java.util.Collection;

public interface DayService {

    Collection<Day> getDays(int pageNumber, int numberOfRecords);
    Day getDayByID(Long id);
    Boolean deleteDayByID(Long id);
    Day saveDay(Day day);
    Day updateDay(Day day);
}
