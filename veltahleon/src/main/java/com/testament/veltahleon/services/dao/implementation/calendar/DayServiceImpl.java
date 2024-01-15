package com.testament.veltahleon.services.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.repositories.dao.ifc.calendar.DayDAO;
import com.testament.veltahleon.services.dao.ifc.calendar.DayService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DayServiceImpl implements DayService {

    @Autowired
    private DayDAO dayDAO;

    @Override
    public Collection<Day> getDays() {
        return dayDAO.getDays();
    }

    @Override
    public Day getDayByID(Long id) {
        return dayDAO.getDayByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteDayByID(Long id) {
        return dayDAO.deleteDayByID(id);
    }

    @Override
    @Transactional
    public Day saveDay(Day day) {
        return dayDAO.saveDay(day);
    }

    @Override
    @Transactional
    public Day updateDay(Day day) {
        return dayDAO.updateDay(day);
    }
}
