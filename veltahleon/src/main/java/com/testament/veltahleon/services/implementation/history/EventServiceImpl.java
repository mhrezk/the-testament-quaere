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
    public Collection<Event> getEventsByYearNumber(int yearNumber) {
        return eventRepository.findByYear_Epoch_YearNumber(yearNumber);
    }

    @Override
    public Collection<Event> getEventsByMonthNumber(int monthNumber) {
        return eventRepository.findByYear_Month_MonthNumber(monthNumber);
    }

    @Override
    public Collection<Event> getEventsByMonthName(String monthName) {
        return eventRepository.findByYear_Month_Name(monthName);
    }

    @Override
    public Collection<Event> getEventsByDayNumber(int dayNumber) {
        return eventRepository.findByYear_Day_DayNumber(dayNumber);
    }

    @Override
    public Collection<Event> getEventsByDayName(String dayName) {
        return eventRepository.findByYear_Day_Name(dayName);
    }

    @Override
    public Event getEventByID(Long id) {
        return eventRepository.findById(id).orElseThrow();
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

        if(event.getYear() != null && newEvent.getYear() != event.getYear()) {
            newEvent.setYear(event.getYear());
        }

        return eventRepository.save(newEvent);
    }

    @Override
    public Event modifyEvent(Long id, Event event) {
        Event newEvent = eventRepository.findById(id).orElseThrow();
        newEvent.setIncident(event.getIncident());
        newEvent.setDescription(event.getDescription());
        newEvent.setYear(event.getYear());
        return null;
    }
}
