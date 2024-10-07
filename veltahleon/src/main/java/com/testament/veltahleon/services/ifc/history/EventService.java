package com.testament.veltahleon.services.ifc.history;

import com.testament.veltahleon.model.entities.history.Event;

import java.util.Collection;

public interface EventService {

    Collection<Event> getEventsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Event> getEvents();
    Collection<Event> getEventsByBeginningYearNumber(int yearNumber);
    Collection<Event> getEventsByBeginningYearMonthNumber(int monthNumber);
    Collection<Event> getEventsByBeginningYearMonthName(String monthName);
    Collection<Event> getEventsByBeginningYearDayNumber(int dayNumber);
    Collection<Event> getEventsByBeginningYearDayName(String dayName);
    Collection<Event> getEventsByEndingYearNumber(int yearNumber);
    Collection<Event> getEventsByEndingYearMonthNumber(int monthNumber);
    Collection<Event> getEventsByEndingYearMonthName(String monthName);
    Collection<Event> getEventsByEndingYearDayNumber(int dayNumber);
    Collection<Event> getEventsByEndingYearDayName(String dayName);
    Event getEventByID(Long id);
    Event getEventByIncidentName(String incidentName);
    Boolean deleteEventByID(Long id);
    Event saveEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event modifyEvent(Long id, Event event);
}
