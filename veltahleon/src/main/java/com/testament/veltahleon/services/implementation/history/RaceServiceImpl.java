package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.repositories.history.RaceRepository;
import com.testament.veltahleon.services.ifc.history.RaceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        if(raceRepository.countByName(name) <= 0) {
            Race newRace = new Race();
//            String firstLetter = name.substring(0, 1).toUpperCase();
//            String word = name.substring(1).toLowerCase();
            newRace.setName(name.toUpperCase());
            return raceRepository.save(newRace);
        } else {
            return raceRepository.findByName(name);
        }
    }

    @Override
    public Boolean deleteRaceByID(Long id) {
        raceRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Race saveRace(Race race) {
        if(raceRepository.countByName(race.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Race name");
        } else {
//            String firstLetter = race.getName().substring(0, 1).toUpperCase();
//            String word = race.getName().substring(1).toLowerCase();
            race.setName(race.getName().toUpperCase());
            race.setImageURL(defaultImageURL("default.png"));
            return raceRepository.save(race);
        }
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/history/races/images/" + imageName).toUriString();
    }

    @Override
    public Race updateRace(Long id, Race race) {
        Race newRace = raceRepository.findById(id).orElseThrow();

        if(race.getName() != null && newRace.getName() != race.getName()) {
            newRace.setName(race.getName().toUpperCase());
        }

        if(race.getDescription() != null && newRace.getDescription() != race.getDescription()) {
            newRace.setDescription(race.getDescription());
        }

        if(race.getImageURL() != null && newRace.getImageURL() != race.getImageURL()) {
            newRace.setImageURL(race.getImageURL());
        }

        return raceRepository.save(newRace);
    }

    @Override
    public Race modifyRace(Long id, Race race) {
        Race newRace = raceRepository.findById(id).orElseThrow();
        newRace.setName(race.getName().toUpperCase());
        newRace.setDescription(race.getDescription());
        newRace.setImageURL(race.getImageURL());
        System.out.println(newRace.getName());
        return raceRepository.save(newRace);
    }

    @Override
    public Boolean doesRaceNameExist(String name) {
        return raceRepository.existsRacesByName(name.toUpperCase());
    }

    @Override
    public Long countRaces() {
        return raceRepository.count();
    }
}
