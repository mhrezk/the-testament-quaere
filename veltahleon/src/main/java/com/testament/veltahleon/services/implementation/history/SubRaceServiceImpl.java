package com.testament.veltahleon.services.implementation.history;

import com.testament.veltahleon.exceptions.DataInsertionException;
import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.history.SubRace;
import com.testament.veltahleon.repositories.history.RaceRepository;
import com.testament.veltahleon.repositories.history.SubRaceRepository;
import com.testament.veltahleon.services.ifc.history.SubRaceService;
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
public class SubRaceServiceImpl implements SubRaceService {

    @Autowired
    private SubRaceRepository subRaceRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public Collection<SubRace> getSubRacesWithPagination(int pageNumber, int pageSize) {
        return subRaceRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Collection<SubRace> getSubRacesWithPaginationByRaceName(String name, int pageNumber, int pageSize) {
        return subRaceRepository.findByRace_Name(name, PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Collection<SubRace> getSubRaces() {
        return subRaceRepository.findAll();
    }

    @Override
    public Collection<SubRace> getSubRacesByRaceName(String name) {
        return subRaceRepository.findByRace_Name(name);
    }

    @Override
    public Boolean deleteSubRaceByID(Long id) {
        subRaceRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean doesSubRaceNameExist(String name) {
        return subRaceRepository.existsSubRaceByName(name.toUpperCase());
    }

    @Override
    public SubRace getSubRaceByID(Long id) {
        return subRaceRepository.findById(id).orElseThrow();
    }

    @Override
    public SubRace getSubRaceByName(String name) {
        return subRaceRepository.findByName(name);
    }

    @Override
    public SubRace saveSubRace(SubRace subRace, String raceName) {
        Race race = raceRepository.findByName(raceName);
        if(subRaceRepository.countByName(subRace.getName().toLowerCase()) >= 1) {
            throw new DataInsertionException("Sub-race name");
        } else {
            subRace.setName(subRace.getName().toUpperCase());
            subRace.setRace(race);
            subRace.setImageURL(defaultImageURL("default.png"));
            return subRaceRepository.save(subRace);
        }
    }

    private String defaultImageURL(String imageName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/history/subRaces/images/" + imageName).toUriString();
    }

    @Override
    public SubRace updateSubRace(Long id, SubRace subRace) {
        SubRace newSubRace = subRaceRepository.findById(id).orElseThrow();

        if(subRace.getName() != null && newSubRace.getName() != subRace.getName()) {
            newSubRace.setName(subRace.getName().toUpperCase());
        }

        if(subRace.getDescription() != null && newSubRace.getDescription() != subRace.getDescription()) {
            newSubRace.setDescription(subRace.getDescription());
        }

        if(subRace.getImageURL() != null && newSubRace.getImageURL() != subRace.getImageURL()) {
            newSubRace.setImageURL(subRace.getImageURL());
        }

        if(subRace.getRace() != null && newSubRace.getRace() != subRace.getRace()) {
            newSubRace.setRace(subRace.getRace());
        }

        return subRaceRepository.save(newSubRace);
    }

    @Override
    public SubRace modifySubRace(Long id, SubRace subRace) {
        SubRace newSubRace = subRaceRepository.findById(id).orElseThrow();
        newSubRace.setName(subRace.getName().toUpperCase());
        newSubRace.setDescription(subRace.getDescription());
        newSubRace.setImageURL(subRace.getImageURL());
        newSubRace.setRace(subRace.getRace());
        return subRaceRepository.save(newSubRace);
    }

    @Override
    public Long countSubRaces() {
        return subRaceRepository.count();
    }

    @Override
    public Long countSubRaceByRaceName(String name) {
        return subRaceRepository.countByRace_Name(name);
    }
}
