package com.testament.veltahleon.services.entities.repo.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar.MonthRepository;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.MonthService;
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


    @Override
    public Collection<Month> getMonthsWithPagination(int pageNumber, int numberOfRecords) {
        return monthRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Month> getMonths() {
        return monthRepository.findAll();
    }

    @Override
    public Collection<Month> getMonthsByLanguageName(String name) {
        return monthRepository.findByLanguage_Name(name);
    }

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

        if(month.getLanguage() != null && newMonth.getLanguage() != month.getLanguage()) {
            newMonth.setLanguage(month.getLanguage());
        }

        return monthRepository.save(newMonth);
    }
}
