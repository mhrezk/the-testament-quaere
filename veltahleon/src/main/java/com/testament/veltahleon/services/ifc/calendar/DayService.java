package com.testament.veltahleon.services.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;

import java.util.Collection;

public interface DayService {

    Collection<Day> getDaysWithPagination(int pageNumber, int numberOfRecords);
    Collection<Day> getDays();
    //Collection<Day> getDaysByLanguage(String languageName);
    Day getDayByID(Long id);
    Day getDayByName(String name);
    Boolean deleteDayByID(Long id);
    Boolean deleteAllDaysByIDs(Collection<Long> dayIDs);
    Boolean deleteAllDays(Collection<Day> days);
    Day saveDay(Day day);
    Collection<Day> saveDays(Collection<Day> days);
    Day updateDay(Long id, Day day);

    Day updateDay(Day day);

    //Day update(Long id, Day day);
}
