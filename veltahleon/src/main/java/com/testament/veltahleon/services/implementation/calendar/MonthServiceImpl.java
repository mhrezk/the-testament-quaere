package com.testament.veltahleon.services.implementation.calendar;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.model.entities.history.Language;
import com.testament.veltahleon.repositories.calendar.MonthRepository;
import com.testament.veltahleon.services.ifc.calendar.MonthService;
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
public class MonthServiceImpl implements MonthService {

    @Autowired
    private MonthRepository monthRepository;
//    @Autowired
//    private final LanguageRepository languageRepository;

    //CRUD
    @Override
    public Collection<Month> getMonthsWithPagination(int pageNumber, int numberOfRecords) {
        return monthRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Month> getMonths() {
        return monthRepository.findAll();
    }

//    @Override
//    public Collection<Month> getMonthsByLanguageName(String name) {
//        return monthRepository.findByLanguage_Name(name);
//    }

    @Override
    public Month getMonthByID(Long id) {
        return monthRepository.findById(id).orElseThrow();
    }

    @Override
    public Month getMonthByName(String name) {
        return monthRepository.findByName(name);
    }

    @Override
    public Boolean deleteMonthByID(Long id) {
        monthRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Month saveMonth(Month month) {
        if(monthRepository.countByName(month.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Month name");
        }

//        if(languageRepository.countByName(month.getLanguage().getName()) >= 1) {
//            Language language = languageRepository.findByName(month.getLanguage().getName());
//            month.getLanguage().setId(language.getId());
//            month.setLanguage(language);
//        }
        return monthRepository.save(month);
    }

    @Override
    public Month updateMonth(Long id, Month month) {
        Month newMonth = monthRepository.findById(id).orElseThrow();

        if(month.getName() != null && newMonth.getName() != month.getName()) {
            newMonth.setName(month.getName());
        }

        if(month.getDescription() != null && newMonth.getDescription() != month.getDescription()) {
            newMonth.setDescription(month.getDescription());
        }

        if(month.getMonthNumber() != null && newMonth.getMonthNumber() != month.getMonthNumber()) {
            newMonth.setMonthNumber(month.getMonthNumber());
        }

        if(month.getNumberOfDays() != null && newMonth.getNumberOfDays() != month.getNumberOfDays()) {
            newMonth.setNumberOfDays(month.getNumberOfDays());
        }

//        if(month.getLanguage() != null && newMonth.getLanguage() != month.getLanguage()) {
//            newMonth.setLanguage(checkLanguageForUpdate(month.getLanguage(), newMonth.getLanguage()));
//        }

        return monthRepository.save(newMonth);
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
