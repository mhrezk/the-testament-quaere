package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.model.entities.history.Event;
import com.testament.veltahleon.repositories.history.EventRepository;
import com.testament.veltahleon.services.ifc.history.EventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Override
    public Collection<Event> getEventsWithPagination(int pageNumber, int numberOfRecords) {
        return eventRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Collection<Event> getEventsByBeginningYearNumber(int yearNumber) {
        return eventRepository.findByBeginningYear_Epoch_YearNumber(yearNumber);
    }

    @Override
    public Collection<Event> getEventsByBeginningYearMonthNumber(int monthNumber) {
        return eventRepository.findByBeginningYear_Month_MonthNumber(monthNumber);
    }

    @Override
    public Collection<Event> getEventsByBeginningYearMonthName(String monthName) {
        return eventRepository.findByBeginningYear_Month_Name(monthName);
    }

    @Override
    public Collection<Event> getEventsByBeginningYearDayNumber(int dayNumber) {
        return eventRepository.findByBeginningYear_Day_DayNumber(dayNumber);
    }

    @Override
    public Collection<Event> getEventsByBeginningYearDayName(String dayName) {
        return eventRepository.findByBeginningYear_Day_Name(dayName);
    }

    @Override
    public Collection<Event> getEventsByEndingYearNumber(int yearNumber) {
        return eventRepository.findByEndingYear_Epoch_YearNumber(yearNumber);
    }

    @Override
    public Collection<Event> getEventsByEndingYearMonthNumber(int monthNumber) {
        return eventRepository.findByEndingYear_Month_MonthNumber(monthNumber);
    }

    @Override
    public Collection<Event> getEventsByEndingYearMonthName(String monthName) {
        return eventRepository.findByEndingYear_Month_Name(monthName);
    }

    @Override
    public Collection<Event> getEventsByEndingYearDayNumber(int dayNumber) {
        return eventRepository.findByEndingYear_Day_DayNumber(dayNumber);
    }

    @Override
    public Collection<Event> getEventsByEndingYearDayName(String dayName) {
        return eventRepository.findByEndingYear_Day_Name(dayName);
    }

    @Override
    public Event getEventByID(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @Override
    public Event getEventByIncidentName(String incidentName) {
        return eventRepository.findByIncident(incidentName);
    }

    @Override
    public Boolean deleteEventByID(Long id) {
        eventRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event newEvent = eventRepository.findById(id).orElseThrow();

        if(event.getIncident() != null && newEvent.getIncident() != event.getIncident()) {
            newEvent.setIncident(event.getIncident());
        }

        if(event.getDescription() != null && newEvent.getDescription() != event.getDescription()) {
            newEvent.setDescription(event.getDescription());
        }

        if(event.getBeginningYear() != null && newEvent.getBeginningYear() != event.getBeginningYear()) {
            newEvent.setBeginningYear(event.getBeginningYear());
        }

        if(event.getEndingYear() != null && newEvent.getEndingYear() != event.getEndingYear()) {
            newEvent.setEndingYear(event.getEndingYear());
        }

        return eventRepository.save(newEvent);
    }

    @Override
    public Event modifyEvent(Long id, Event event) {
        Event newEvent = eventRepository.findById(id).orElseThrow();
        newEvent.setIncident(event.getIncident());
        newEvent.setDescription(event.getDescription());
        newEvent.setBeginningYear(event.getBeginningYear());
        newEvent.setEndingYear(event.getEndingYear());
        return eventRepository.save(newEvent);
    }
}