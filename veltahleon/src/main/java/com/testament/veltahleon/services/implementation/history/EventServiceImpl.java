package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.model.entities.history.Event;
import com.testament.veltahleon.model.entities.history.Timeline;
import com.testament.veltahleon.repositories.history.EventRepository;
import com.testament.veltahleon.repositories.history.TimelineRepository;
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

    @Autowired
    private final TimelineRepository timelineRepository;

    @Override
    public Collection<Event> getPaginatedEvents(int pageNumber, int numberOfRecords) {
        return eventRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Event> getPaginatedEventsByTimelineName(String timelineName, int pageNumber, int numberOfRecords) {
        return eventRepository.findByTimeline_Name(timelineName, PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Collection<Event> getEventsByEventYear(int year) {
        return eventRepository.findByEventYear(year);
    }

    @Override
    public Collection<Event> getEventsByTimeline(String timelineName) {
        return eventRepository.findByTimeline_Name(timelineName);
    }

//    @Override
//    public Collection<Event> getEventsByBeginningYearNumber(int yearNumber) {
//        return eventRepository.findByBeginningYear_Epoch_YearNumber(yearNumber);
//    }
//
//    @Override
//    public Collection<Event> getEventsByBeginningYearMonthNumber(int monthNumber) {
//        return eventRepository.findByBeginningYear_Month_MonthNumber(monthNumber);
//    }
//
//    @Override
//    public Collection<Event> getEventsByBeginningYearMonthName(String monthName) {
//        return eventRepository.findByBeginningYear_Month_Name(monthName);
//    }
//
//    @Override
//    public Collection<Event> getEventsByBeginningYearDayNumber(int dayNumber) {
//        return eventRepository.findByBeginningYear_Day_DayNumber(dayNumber);
//    }
//
//    @Override
//    public Collection<Event> getEventsByBeginningYearDayName(String dayName) {
//        return eventRepository.findByBeginningYear_Day_Name(dayName);
//    }
//
//    @Override
//    public Collection<Event> getEventsByEndingYearNumber(int yearNumber) {
//        return eventRepository.findByEndingYear_Epoch_YearNumber(yearNumber);
//    }
//
//    @Override
//    public Collection<Event> getEventsByEndingYearMonthNumber(int monthNumber) {
//        return eventRepository.findByEndingYear_Month_MonthNumber(monthNumber);
//    }
//
//    @Override
//    public Collection<Event> getEventsByEndingYearMonthName(String monthName) {
//        return eventRepository.findByEndingYear_Month_Name(monthName);
//    }
//
//    @Override
//    public Collection<Event> getEventsByEndingYearDayNumber(int dayNumber) {
//        return eventRepository.findByEndingYear_Day_DayNumber(dayNumber);
//    }
//
//    @Override
//    public Collection<Event> getEventsByEndingYearDayName(String dayName) {
//        return eventRepository.findByEndingYear_Day_Name(dayName);
//    }

    @Override
    public Event getEventByID(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    @Override
    public Event getEventByIncidentName(String incidentName) {
        return eventRepository.findByIncident(incidentName);
    }

    @Override
    public Event getEventByEventDayAndEventMonthAndEventYear(int day, int month, int year) {
        return eventRepository.findByEventDayAndEventMonthAndEventYear(day, month, year);
    }

    @Override
    public Boolean deleteEventByID(Long id) {
        eventRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Event saveEvent(Event event, String timelineName) {
        Timeline timeline = timelineRepository.findByName(timelineName);
        event.setTimeline(timeline);
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

        if(event.getEventDay() != null && newEvent.getEventDay() != event.getEventDay()) {
            newEvent.setEventDay(event.getEventDay());
        }

        if(event.getEventMonth() != null && newEvent.getEventMonth() != event.getEventMonth()) {
            newEvent.setEventMonth(event.getEventMonth());
        }

        if(event.getEventYear() != null && newEvent.getEventYear() != event.getEventYear()) {
            newEvent.setEventYear(event.getEventYear());
        }

        if(event.getTimeline() != null && newEvent.getTimeline() != event.getTimeline()) {
            newEvent.setTimeline(event.getTimeline());
        }

//        if(event.getBeginningYear() != null && newEvent.getBeginningYear() != event.getBeginningYear()) {
//            newEvent.setBeginningYear(event.getBeginningYear());
//        }
//
//        if(event.getEndingYear() != null && newEvent.getEndingYear() != event.getEndingYear()) {
//            newEvent.setEndingYear(event.getEndingYear());
//        }

        return eventRepository.save(newEvent);
    }

    @Override
    public Event modifyEvent(Long id, Event event) {
        Event newEvent = eventRepository.findById(id).orElseThrow();
        newEvent.setIncident(event.getIncident());
        newEvent.setDescription(event.getDescription());
        newEvent.setEventDay(event.getEventDay());
        newEvent.setEventMonth(event.getEventMonth());
        newEvent.setEventYear(event.getEventYear());
        newEvent.setTimeline(event.getTimeline());
        newEvent.setYearAbbreviation(event.getYearAbbreviation());
        return eventRepository.save(newEvent);
    }
}
