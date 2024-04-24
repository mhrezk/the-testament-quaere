package com.testament.veltahleon.services.ifc.calendar;

import com.testament.veltahleon.model.entities.calendar.Calendar;

import java.util.Collection;

public interface CalendarService {

    Collection<Calendar> getCalendarWithPagination(int pageNumber, int numberOfRecords);
    Collection<Calendar> getCalendars();
    Calendar getCalendarByID(Long id);
    Calendar getCalendarByName(String name);
    Boolean deleteCalendarByID(Long id);
    Calendar saveCalendar(Calendar calendar);
    Calendar updateCalendar(Long id, Calendar calendar);
    Calendar modifyCalendar(Long id, Calendar calendar);
}
