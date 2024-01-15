package com.testament.veltahleon.services.dao.implementation.history;

import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.repositories.dao.ifc.history.RaceDAO;
import com.testament.veltahleon.services.dao.ifc.history.RaceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceDAO raceDAO;

    @Override
    public Collection<Race> getRaces() {
        return raceDAO.getRaces();
    }

    @Override
    public Race getRaceByID(Long id) {
        return raceDAO.getRaceByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteRaceByID(Long id) {
        return raceDAO.deleteRaceByID(id);
    }

    @Override
    @Transactional
    public Race saveRace(Race race) {
        return raceDAO.saveRace(race);
    }

    @Override
    @Transactional
    public Race updateRace(Race race) {
        return raceDAO.updateRace(race);
    }
}
