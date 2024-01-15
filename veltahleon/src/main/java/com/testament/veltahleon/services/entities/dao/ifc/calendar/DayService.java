package com.testament.veltahleon.services.entities.dao.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;

import java.util.Collection;

public interface DayService {

    Collection<Day> getDays();
    Day getDayByID(Long id);
    Boolean deleteDayByID(Long id);
    Day saveDay(Day day);
    Day updateDay(Day day);
}
