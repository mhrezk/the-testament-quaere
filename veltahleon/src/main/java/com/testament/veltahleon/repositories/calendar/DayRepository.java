package com.testament.veltahleon.repositories.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.history.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    Day findByName(String name);
    //Collection<Day> findByLanguage_Name(String languageName);
    Collection<Day> findByDayNumber(int number);
    //Collection<Day> findByLanguageName(String languageName);
    long countByName(String name);
    //long countByLanguage_Name(String languageName);
}
