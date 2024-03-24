package com.testament.veltahleon.services.implementation.calendar;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.repositories.calendar.DayRepository;
import com.testament.veltahleon.services.ifc.calendar.DayService;
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

//    @Override
//    public Collection<Day> getDaysByLanguage(String languageName) {
//        return dayRepository.findByLanguage_Name(languageName);
//    }

//    @Override
//    public Collection<Day> getDaysByLanguage(String languageName) {
//        return dayRepository.findByLanguage_Name(languageName);
//    }

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
        if(dayRepository.countByName(day.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Day name");
        }

//        if(languageRepository.countByName(day.getLanguage().getName()) >= 1) {
//            Language language = languageRepository.findByName(day.getLanguage().getName());
//            day.getLanguage().setId(language.getId());
//            day.setLanguage(language);
//        }
        log.info("Day saved!");
        return dayRepository.save(day);
    }

    @Override
    public Collection<Day> saveDays(Collection<Day> days) {
        log.info("Days saved!");
        return dayRepository.saveAll(days);
    }

    @Override
    public Day updateDay(Long id, Day day) {
        Day newDay = dayRepository.findById(id).orElseThrow();

        if(day.getName() != null && newDay.getName() != day.getName()) {
            newDay.setName(day.getName());
        }

        if(day.getDayNumber() != null && newDay.getDayNumber() != day.getDayNumber()) {
            newDay.setDayNumber(day.getDayNumber());
        }

//        if((day.getLanguage() != null && newDay.getLanguage() != day.getLanguage())) {
//            newDay.setLanguage(checkLanguageForUpdate(day.getLanguage(), newDay.getLanguage()));
//        }

        if(day.getDescription() != null && newDay.getDescription() != day.getDescription()) {
            newDay.setDescription(day.getDescription());
        }
        return dayRepository.save(newDay);
    }

    @Override
    public Day updateDay(Day day) {
        return dayRepository.save(day);
    }

    //Helper Methods
//    private Language checkLanguageForUpdate(Language language, Language newLanguage) {
//        if(language.getName() != null && newLanguage.getName() != language.getName()) {
//            newLanguage.setName(language.getName());
//        }
//
//        if(language.getDescription() != null && newLanguage.getDescription() != language.getDescription()) {
//            newLanguage.setDescription(language.getDescription());
//        }
//
//        if(language.getAlphabetURL() != null && newLanguage.getAlphabetURL() != language.getAlphabetURL()) {
//            newLanguage.setAlphabetURL(language.getAlphabetURL());
//        }
//        return newLanguage;
//    }
}
