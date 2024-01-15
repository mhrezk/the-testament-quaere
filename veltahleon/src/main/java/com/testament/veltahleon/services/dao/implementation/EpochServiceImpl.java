package com.testament.veltahleon.services.dao.implementation;

import com.testament.veltahleon.repositories.dao.ifc.calendar.EpochDAO;
import com.testament.veltahleon.services.dao.ifc.calendar.EpochService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpochServiceImpl implements EpochService {

    @Autowired
    private EpochDAO epochDAO;
}
