package com.testament.veltahleon.services.implementation.calendar;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.exceptions.DataNotFoundException;
import com.testament.veltahleon.model.entities.calendar.CalendarSystem;
import com.testament.veltahleon.repositories.calendar.CalendarSystemRepository;
import com.testament.veltahleon.services.ifc.calendar.CalendarSystemService;
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
public class CalendarSystemServiceImpl implements CalendarSystemService {

    @Autowired
    private CalendarSystemRepository calendarSystemRepository;

    @Override
    public Collection<CalendarSystem> getCalendarWithPagination(int pageNumber, int numberOfRecords) {
        return calendarSystemRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<CalendarSystem> getCalendars() {
        return calendarSystemRepository.findAll();
    }

    @Override
    public CalendarSystem getCalendarByID(Long id) {
        return calendarSystemRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));
    }

    @Override
    public CalendarSystem getCalendarByName(String name) {
        return calendarSystemRepository.findByName(name);
    }

    @Override
    public Boolean deleteCalendarByID(Long id) {
        calendarSystemRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public CalendarSystem saveCalendar(CalendarSystem calendarSystem) {
        if(calendarSystemRepository.countByName(calendarSystem.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Calendar name");
        }
        return calendarSystemRepository.save(calendarSystem);
    }

    @Override
    public CalendarSystem updateCalendar(Long id, CalendarSystem calendarSystem) {
        CalendarSystem newCalendarSystem = calendarSystemRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));

        if(calendarSystem.getName() != null && !newCalendarSystem.getName().equals(calendarSystem.getName())) {
            newCalendarSystem.setName(calendarSystem.getName());
        }

        if(calendarSystem.getDays() != null && newCalendarSystem.getDays() != calendarSystem.getDays()) {
            newCalendarSystem.setDays(calendarSystem.getDays());
        }

        if(calendarSystem.getMonths() != null && newCalendarSystem.getMonths() != calendarSystem.getMonths()) {
            newCalendarSystem.setMonths(calendarSystem.getMonths());
        }

//        if(calendarSystem.getEpochs() != null && newCalendarSystem.getEpochs() != calendarSystem.getEpochs()) {
//            newCalendarSystem.setEpochs(calendarSystem.getEpochs());
//        }

        return calendarSystemRepository.save(newCalendarSystem);
    }

    @Override
    public CalendarSystem modifyCalendar(Long id, CalendarSystem calendarSystem) {
        CalendarSystem newCalendarSystem = calendarSystemRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Datum does not exist in database!"));
        newCalendarSystem.setName(calendarSystem.getName());
        newCalendarSystem.setDays(calendarSystem.getDays());
        newCalendarSystem.setMonths(calendarSystem.getMonths());
        //newCalendarSystem.setEpochs(calendarSystem.getEpochs());
        return calendarSystemRepository.save(newCalendarSystem);
    }
}
