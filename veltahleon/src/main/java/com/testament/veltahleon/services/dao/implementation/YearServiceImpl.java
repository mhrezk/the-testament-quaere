package com.testament.veltahleon.services.dao.implementation;

import com.testament.veltahleon.repositories.dao.ifc.calendar.DayDAO;
import com.testament.veltahleon.repositories.dao.ifc.calendar.YearDAO;
import com.testament.veltahleon.services.dao.ifc.calendar.YearService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YearServiceImpl implements YearService {

    @Autowired
    private YearDAO yearDAO;
}
