package com.testament.veltahleon.services.entities.repo.implementation.calendar;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar.DayRepository;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LanguageRepository;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LetterRepository;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.DayService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    private final EntityManager entityManager;

    @Autowired
    private final DayRepository dayRepository;

    @Autowired
    private final LanguageRepository languageRepository;


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
        return dayRepository.findByLanguageName(languageName);
    }

    @Override
    public Day getDayByID(Long id) {
        return dayRepository.findById(id).get();
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
        validateDayEntry(day);
        String properName = capitalizeName(day.getName());
        day.setName(properName);
        if(day.getLanguage() != null) {
            String languageProperName = capitalizeName(day.getLanguage().getName());
            day.getLanguage().setName(languageProperName);
        }
        log.info("Day saved!");
        return dayRepository.save(day);
    }

    @Override
    public Collection<Day> saveDays(Collection<Day> days) {
        for(Day day : days) {
            validateDayEntry(day);
            String properName = capitalizeName(day.getName());
            if(day.getLanguage() != null) {
                String languageProperName = capitalizeName(day.getLanguage().getName());
                day.getLanguage().setName(languageProperName);
            }
            day.setName(properName);
        }
        return dayRepository.saveAll(days);
    }

    @Override
    public Day updateDay(Long id, Day day) {
        Day newDay = dayRepository.findById(id).get();

        if(day.getName() != null && newDay.getName() != day.getName()) {
            String properName = capitalizeName(day.getName());
            newDay.setName(properName);
        }

        if(day.getDayNumber() != null && newDay.getDayNumber() != day.getDayNumber()) {
            newDay.setDayNumber(day.getDayNumber());
        }

        if((day.getLanguage() != null && newDay.getLanguage() != day.getLanguage())) {
            String languageProperName = capitalizeName(day.getLanguage().getName());
            if(languageRepository.countByName(day.getLanguage().getName()) >= 1) {
                Language language = languageRepository.findByName(languageProperName);
                newDay.setLanguage(language);
            } else {
                day.getLanguage().setName(languageProperName);
                newDay.setLanguage(day.getLanguage());
            }
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

    private void validateDayEntry(Day day) {
        if(day.getName() == null || day.getName().isEmpty() || day.getName().isBlank()) {
            throw new DataInsertionException("Day name must be present!");
        }

//        if(day.getLanguage().getName() == null || day.getLanguage().getName().isEmpty() || day.getLanguage().getName().isBlank()) {
//            throw new DataInsertionException("Language name must be present!");
//        }

        if(dayRepository.countByName(day.getName()) >= 1) {
            throw new DataInsertionException("Day name already exists! Duplicate entries are disallowed!");
        }

        //To avoid adding different entries with the same language name
        if(day.getLanguage() != null) {
            if(languageRepository.countByName(day.getLanguage().getName()) >= 1) {
                Language language = languageRepository.findByName(day.getLanguage().getName());
                day.getLanguage().setId(language.getId());
                day.setLanguage(language);
            }
        }
    }

    public Language queryLanguageByName(String name) {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language WHERE name=:languageName", Language.class);
        query.setParameter("languageName", name);
        return query.getSingleResult();
    }
}
