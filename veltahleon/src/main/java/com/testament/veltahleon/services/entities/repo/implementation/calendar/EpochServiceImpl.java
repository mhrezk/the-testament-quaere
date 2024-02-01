package com.testament.veltahleon.services.entities.repo.implementation.calendar;

import com.testament.veltahleon.model.entities.calendar.Epoch;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.calendar.EpochRepository;
import com.testament.veltahleon.services.entities.repo.ifc.calendar.EpochService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EpochServiceImpl implements EpochService {

    @Autowired
    private EpochRepository epochRepository;


    @Override
    public Collection<Epoch> getEpochs() {
        return epochRepository.findAll();
    }

    @Override
    public Epoch getEpochByID(Long id) {
        return epochRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteEpochByID(Long id) {
        epochRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Epoch saveEpoch(Epoch epoch) {
        return epochRepository.save(epoch);
    }

    @Override
    public Epoch updateEpoch(Long id, Epoch epoch) {
        Epoch newEpoch = epochRepository.findById(id).orElseThrow();

        if(epoch.getDescription() != null && newEpoch.getDescription() != epoch.getDescription()) {
            newEpoch.setDescription(epoch.getDescription());
        }

        if(epoch.getYearNumber() != null && newEpoch.getYearNumber() != epoch.getYearNumber()) {
            newEpoch.setYearNumber(epoch.getYearNumber());
        }

        if(epoch.getYearName() != null && newEpoch.getYearName() != epoch.getYearName()) {
            newEpoch.setYearName(epoch.getYearName());
        }

        if(epoch.getAbbreviation() != null && newEpoch.getAbbreviation() != epoch.getAbbreviation()) {
            newEpoch.setAbbreviation(epoch.getAbbreviation());
        }

        return epochRepository.save(newEpoch);
    }
}
