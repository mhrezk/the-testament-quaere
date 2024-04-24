package com.testament.veltahleon.services.implementation.calendar;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.calendar.Calendar;
import com.testament.veltahleon.repositories.calendar.CalendarRepository;
import com.testament.veltahleon.services.ifc.calendar.CalendarService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public Collection<Calendar> getCalendarWithPagination(int pageNumber, int numberOfRecords) {
        return calendarRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Calendar> getCalendars() {
        return calendarRepository.findAll();
    }

    @Override
    public Calendar getCalendarByID(Long id) {
        return calendarRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));
    }

    @Override
    public Calendar getCalendarByName(String name) {
        return calendarRepository.findByName(name);
    }

    @Override
    public Boolean deleteCalendarByID(Long id) {
        calendarRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Calendar saveCalendar(Calendar calendar) {
        if(calendarRepository.countByName(calendar.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Calendar name");
        }
        return calendarRepository.save(calendar);
    }

    @Override
    public Calendar updateCalendar(Long id, Calendar calendar) {
        Calendar newCalendar = calendarRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));

        if(calendar.getName() != null && !newCalendar.getName().equals(calendar.getName())) {
            newCalendar.setName(calendar.getName());
        }

        if(calendar.getDays() != null && newCalendar.getDays() != calendar.getDays()) {
            newCalendar.setDays(calendar.getDays());
        }

        if(calendar.getMonths() != null && newCalendar.getMonths() != calendar.getMonths()) {
            newCalendar.setMonths(calendar.getMonths());
        }

        if(calendar.getEpochs() != null && newCalendar.getEpochs() != calendar.getEpochs()) {
            newCalendar.setEpochs(calendar.getEpochs());
        }

        return calendarRepository.save(newCalendar);
    }

    @Override
    public Calendar modifyCalendar(Long id, Calendar calendar) {
        Calendar newCalendar = calendarRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));
        newCalendar.setName(calendar.getName());
        newCalendar.setDays(calendar.getDays());
        newCalendar.setMonths(calendar.getMonths());
        newCalendar.setEpochs(calendar.getEpochs());
        return calendarRepository.save(newCalendar);
    }
}
