package com.testament.veltahleon.services.dao.implementation;

import com.testament.veltahleon.repositories.dao.ifc.calendar.MonthDAO;
import com.testament.veltahleon.services.dao.ifc.calendar.MonthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthServiceImpl implements MonthService {

    @Autowired
    private MonthDAO monthDAO;
}
