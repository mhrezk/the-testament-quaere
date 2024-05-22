package com.testament.veltahleon.services.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.CalendarSystem;

import java.util.Collection;

public interface CalendarSystemService {

    Collection<CalendarSystem> getCalendarWithPagination(int pageNumber, int numberOfRecords);
    Collection<CalendarSystem> getCalendars();
    CalendarSystem getCalendarByID(Long id);
    CalendarSystem getCalendarByName(String name);
    Boolean deleteCalendarByID(Long id);
    CalendarSystem saveCalendar(CalendarSystem calendarSystem);
    CalendarSystem updateCalendar(Long id, CalendarSystem calendarSystem);
    CalendarSystem modifyCalendar(Long id, CalendarSystem calendarSystem);
}
