package com.testament.veltahleon.services.entities.dao.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.repositories.dao.ifc.calendar.EpochDAO;
import com.testament.veltahleon.services.entities.dao.ifc.calendar.EpochService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class EpochServiceImpl implements EpochService {

    @Autowired
    private EpochDAO epochDAO;

    @Override
    public Collection<Epoch> getEpochs() {
        return epochDAO.getEpochs();
    }

    @Override
    public Epoch getEpochByID(Long id) {
        return epochDAO.getEpochByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteEpochByID(Long id) {
        return epochDAO.deleteEpochByID(id);
    }

    @Override
    @Transactional
    public Epoch saveEpoch(Epoch epoch) {
        return epochDAO.saveEpoch(epoch);
    }

    @Override
    @Transactional
    public Epoch updateEpoch(Epoch epoch) {
        return epochDAO.updateEpoch(epoch);
    }
}
