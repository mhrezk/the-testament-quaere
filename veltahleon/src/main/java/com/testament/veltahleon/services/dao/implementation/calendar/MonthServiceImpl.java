package com.testament.veltahleon.services.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Month;
import com.testament.veltahleon.repositories.dao.ifc.calendar.MonthDAO;
import com.testament.veltahleon.services.dao.ifc.calendar.MonthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class MonthServiceImpl implements MonthService {

    @Autowired
    private MonthDAO monthDAO;

    @Override
    public Collection<Month> getMonths() {
        return monthDAO.getMonths();
    }

    @Override
    public Month getMonthByID(Long id) {
        return monthDAO.getMonthByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteMonthByID(Long id) {
        return monthDAO.deleteMonthByID(id);
    }

    @Override
    @Transactional
    public Month saveMonth(Month month) {
        return monthDAO.saveMonth(month);
    }

    @Override
    @Transactional
    public Month updateMonth(Month month) {
        return monthDAO.updateMonth(month);
    }
}
