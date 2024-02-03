package com.testament.veltahleon.services.entities.repo.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar.DayRepository;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.DayService;
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
public class DayServiceImpl implements DayService {

    @Autowired
    private final DayRepository dayRepository;

    //CRUD

    @Override
    public Collection<Day> getDaysWithPagination(int pageNumber, int numberOfRecords) {
        return dayRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Day> getDays() {
        return dayRepository.findAll();
    }

    @Override
    public Collection<Day> getDaysByLanguage(String languageName) {
        return dayRepository.findByLanguage_Name(languageName);
    }

    @Override
    public Day getDayByID(Long id) {
        return dayRepository.findById(id).orElseThrow();
    }

    @Override
    public Day getDayByName(String name) {
        return dayRepository.findByName(name);
    }

    @Override
    public Boolean deleteDayByID(Long id) {
        dayRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAllDaysByIDs(Collection<Long> dayIDs) {
        dayRepository.deleteAllById(dayIDs);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAllDays(Collection<Day> days) {
        dayRepository.deleteAll(days);
        return Boolean.TRUE;
    }

    @Override
    public Day saveDay(Day day) {
        log.info("Day saved!");
        return dayRepository.save(day);
    }

    @Override
    public Collection<Day> saveDays(Collection<Day> days) {
        return dayRepository.saveAll(days);
    }

    @Override
    public Day updateDay(Long id, Day day) {
        Day newDay = dayRepository.findById(id).get();

        if(day.getName() != null && newDay.getName() != day.getName()) {
            newDay.setName(day.getName());
        }

        if(day.getDayNumber() != null && newDay.getDayNumber() != day.getDayNumber()) {
            newDay.setDayNumber(day.getDayNumber());
        }

        if((day.getLanguage() != null && newDay.getLanguage() != day.getLanguage())) {
            newDay.setLanguage(day.getLanguage());
        }

        if(day.getDescription() != null && newDay.getDescription() != day.getDescription()) {
            newDay.setDescription(day.getDescription());
        }
        return dayRepository.save(newDay);
    }

    //Helper Methods
    private String capitalizeName(String word) {
        String firstCharacter = word.toLowerCase().substring(0, 1).toUpperCase();
        return firstCharacter + word.substring(1);
    }
}
