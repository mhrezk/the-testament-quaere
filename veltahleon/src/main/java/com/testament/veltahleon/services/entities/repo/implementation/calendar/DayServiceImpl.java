package com.testament.veltahleon.services.entities.repo.implementation.calendar;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar.DayRepository;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.history.LanguageRepository;
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
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayServiceImpl implements DayService {

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    private final DayRepository dayRepository;

    @Autowired
    private final LanguageRepository languageRepository;

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
    @Transactional
    public Boolean deleteDayByID(Long id) {
        dayRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean deleteAllDaysByIDs(Collection<Long> dayIDs) {
        dayRepository.deleteAllById(dayIDs);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean deleteAllDays(Collection<Day> days) {
        dayRepository.deleteAll(days);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Day saveDay(Day day) {
        validateDayEntry(day);
        log.info("Day saved!");
        return dayRepository.save(day);
    }

    private void validateDayEntry(Day day) {
        if(day.getName() == null) {
            throw new DataInsertionException("Day name cannot be null!");
        }

        if(day.getLanguage() == null) {
            throw new DataInsertionException("Language cannot be null!");
        }

        if(day.getLanguage().getName() == null) {
            throw new DataInsertionException("Language name cannot be null!");
        }

        if(dayRepository.countByName(day.getName()) >= 1) {
            throw new DataInsertionException("Day name already exists! Duplicate entries are disallowed!");
        }

        if(languageRepository.countByName(day.getLanguage().getName()) >= 1) {
            Language language = languageRepository.findByName(day.getLanguage().getName());
            day.getLanguage().setId(language.getId());
            day.setLanguage(language);
        }
    }

    @Override
    @Transactional
    public Collection<Day> saveDays(Collection<Day> days) {
        for(Day day : days) {
            validateDayEntry(day);
        }
        return dayRepository.saveAll(days);
    }

    @Override
    @Transactional
    public Day updateDay(Long id, Day day) {
        Day newDay = dayRepository.findById(id).get();
        Language language = newDay.getLanguage();

        if(newDay.getName() != day.getName()) {
            newDay.setName(day.getName());
        }

        if(newDay.getDayNumber() != day.getDayNumber()) {
            newDay.setDayNumber(day.getDayNumber());
        }

        if(language != day.getLanguage()) {
            language.setName(day.getLanguage().getName());
            language.setLetters(day.getLanguage().getLetters());
            language.setNationalAffiliation(day.getLanguage().getNationalAffiliation());
            language.setDescription(day.getLanguage().getDescription());
            newDay.setLanguage(language);
        }

        if(day.getDescription() != null && newDay.getDescription() != day.getDescription()) {
            newDay.setDescription(day.getDescription());
        }
        return dayRepository.save(newDay);
    }

    public Language checkLanguageInDatabase(String name) {
        TypedQuery<Language> query = entityManager.createQuery("FROM Language WHERE name=:languageName", Language.class);
        query.setParameter("languageName", name);
        return query.getSingleResult();
    }
}
