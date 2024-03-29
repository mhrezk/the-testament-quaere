package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.repositories.history.RaceRepository;
import com.testament.veltahleon.services.ifc.history.RaceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Collection<Race> getRacesWithPagination(int pageNumber, int numberOfRecords) {
        return raceRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Race> getRaces() {
        return raceRepository.findAll();
    }

    @Override
    public Race getRaceByID(Long id) {
        return raceRepository.findById(id).orElseThrow();
    }

    @Override
    public Race getRaceByName(String name) {
        return raceRepository.findByName(name);
    }

    @Override
    public Boolean deleteRaceByID(Long id) {
        raceRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Race saveRace(Race race) {
        return raceRepository.save(race);
    }

    @Override
    public Race updateRace(Long id, Race race) {
        Race newRace = raceRepository.findById(id).orElseThrow();

        if(race.getName() != null && newRace.getName() != race.getName()) {
            newRace.setName(race.getName());
        }

        if(race.getDescription() != null && newRace.getDescription() != race.getDescription()) {
            newRace.setDescription(race.getDescription());
        }

        if(race.getImageURL() != null && newRace.getImageURL() != race.getImageURL()) {
            newRace.setImageURL(race.getImageURL());
        }

        return raceRepository.save(newRace);
    }
}
