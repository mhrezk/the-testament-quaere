package com.testament.veltahleon.services.entities.repo.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar.DayRepository;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.DayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DayServiceImpl implements DayService {

    @Autowired
    private final DayRepository dayRepository;

    @Override
    public Collection<Day> getDays(int pageNumber, int numberOfRecords) {
        return dayRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Day getDayByID(Long id) {
        return null;
    }

    @Override
    public Boolean deleteDayByID(Long id) {
        return null;
    }

    @Override
    public Day saveDay(Day day) {
        return null;
    }

    @Override
    public Day updateDay(Day day) {
        return null;
    }
}
