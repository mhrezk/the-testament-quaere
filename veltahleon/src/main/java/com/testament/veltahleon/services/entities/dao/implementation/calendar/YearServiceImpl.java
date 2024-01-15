package com.testament.veltahleon.services.entities.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Year;
import com.testament.veltahleon.repositories.dao.ifc.calendar.YearDAO;
import com.testament.veltahleon.services.entities.dao.ifc.calendar.YearService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class YearServiceImpl implements YearService {

    @Autowired
    private YearDAO yearDAO;

    @Override
    public Collection<Year> getYears() {
        return yearDAO.getYears();
    }

    @Override
    public Year getYearByID(Long id) {
        return yearDAO.getYearByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteYearByID(Long id) {
        return yearDAO.deleteYearByID(id);
    }

    @Override
    @Transactional
    public Year saveYear(Year year) {
        return yearDAO.saveYear(year);
    }

    @Override
    @Transactional
    public Year updateYear(Year year) {
        return yearDAO.updateYear(year);
    }
}
