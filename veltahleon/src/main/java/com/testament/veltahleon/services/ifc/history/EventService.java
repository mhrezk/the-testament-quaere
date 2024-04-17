package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Event;

import java.util.Collection;

public interface EventService {

    Collection<Event> getEventsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Event> getEvents();
    Collection<Event> getEventsByYearNumber(int yearNumber);
    Collection<Event> getEventsByMonthNumber(int monthNumber);
    Collection<Event> getEventsByMonthName(String monthName);
    Collection<Event> getEventsByDayNumber(int dayNumber);
    Collection<Event> getEventsByDayName(String dayName);
    Event getEventByID(Long id);
    Boolean deleteEventByID(Long id);
    Event saveEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event modifyEvent(Long id, Event event);
}
