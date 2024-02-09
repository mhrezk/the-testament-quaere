package com.testament.veltahleon.services.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.repositories.calendar.YearRepository;
import com.testament.veltahleon.services.ifc.calendar.YearService;
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
public class YearServiceImpl implements YearService {

    @Autowired
    private YearRepository yearRepository;

    @Override
    public Collection<Year> getYearsWithPagination(int pageNumber, int numberOfRecords) {
        return yearRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Year> getYears() {
        return yearRepository.findAll();
    }

    @Override
    public Year getYearByID(Long id) {
        return yearRepository.findById(id).orElseThrow();
    }

    @Override
    public Year getYearByDate(Integer day, Integer month, Integer year) {
        return yearRepository.findByDay_DayNumberAndMonth_MonthNumberAndEpoch_YearNumber(day, month, year);
    }

    @Override
    public Collection<Year> getYearByName(String name) {
        return yearRepository.findByName(name);
    }

    @Override
    public Boolean deleteYearByID(Long id) {
        yearRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Year saveYear(Year year) {
        return yearRepository.save(year);
    }

    @Override
    public Year updateYear(Long id, Year year) {
        Year newYear = yearRepository.findById(id).orElseThrow();

        if(year.getName() != null && newYear.getName() != year.getName()) {
            newYear.setName(year.getName());
        }

        if(year.getDay() != null && newYear.getDay() != year.getDay()) {
            newYear.setDay(year.getDay());
        }

        if(year.getMonth() != null && newYear.getMonth() != year.getMonth()) {
            newYear.setMonth(year.getMonth());
        }

        if(year.getEpoch() != null && newYear.getEpoch() != year.getEpoch()) {
            newYear.setEpoch(year.getEpoch());
        }

        return yearRepository.save(newYear);
    }
}
