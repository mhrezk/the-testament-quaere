package com.testament.veltahleon.services.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.SquadLeader;
import com.testament.veltahleon.repositories.politics.military.SquadLeaderRepository;
import com.testament.veltahleon.services.ifc.politics.military.SquadLeaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SquadLeaderServiceImpl implements SquadLeaderService {

    @Autowired
    private SquadLeaderRepository squadLeaderRepository;

    @Override
    public Collection<SquadLeader> getSquadLeadersWithPagination(int pageNumber, int numberOfRecords) {
        return squadLeaderRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<SquadLeader> getSquadLeaders() {
        return squadLeaderRepository.findAll();
    }

    @Override
    public SquadLeader getSquadLeaderByID(Long id) {
        return squadLeaderRepository.findById(id).orElseThrow();
    }

    @Override
    public SquadLeader getSquadLeaderByName(String name) {
        return squadLeaderRepository.findByName(name);
    }

    @Override
    public Boolean deleteSquadLeaderByID(Long id) {
        squadLeaderRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public SquadLeader saveSquadLeader(SquadLeader squadLeader) {
        return squadLeaderRepository.save(squadLeader);
    }

    @Override
    public SquadLeader updateSquadLeader(Long id, SquadLeader squadLeader) {
        SquadLeader newSquadLeader = squadLeaderRepository.findById(id).orElseThrow();

        if(squadLeader.getName() != null && newSquadLeader.getName() != squadLeader.getName()) {
            newSquadLeader.setName(squadLeader.getName());
        }

        if(squadLeader.getNation() != null && newSquadLeader.getNation() != squadLeader.getNation()) {
            newSquadLeader.setNation(squadLeader.getNation());
        }

        if(squadLeader.getRace() != null && newSquadLeader.getRace() != squadLeader.getRace()) {
            newSquadLeader.setRace(squadLeader.getRace());
        }

        if(squadLeader.getImageURL() != null && newSquadLeader.getImageURL() != squadLeader.getImageURL()) {
            newSquadLeader.setImageURL(squadLeader.getImageURL());
        }

        if(squadLeader.getBiography() != null && newSquadLeader.getBiography() != squadLeader.getBiography()) {
            newSquadLeader.setBiography(squadLeader.getBiography());
        }

        if(squadLeader.getFamily() != null && newSquadLeader.getFamily() != squadLeader.getFamily()) {
            newSquadLeader.setFamily(squadLeader.getFamily());
        }

        if(squadLeader.getReligion() != null && newSquadLeader.getReligion() != squadLeader.getReligion()) {
            newSquadLeader.setReligion(squadLeader.getReligion());
        }

        if(squadLeader.getBattles() != null && newSquadLeader.getBattles() != squadLeader.getBattles()) {
            newSquadLeader.setBattles(squadLeader.getBattles());
        }

        if(squadLeader.getRank() != null && newSquadLeader.getRank() != squadLeader.getRank()) {
            newSquadLeader.setRank(squadLeader.getRank());
        }

        if(squadLeader.getFamily() != null && newSquadLeader.getFamily() != squadLeader.getFamily()) {
            newSquadLeader.setFamily(squadLeader.getFamily());
        }

        if(squadLeader.getYearBirthAndDeath() != null && newSquadLeader.getYearBirthAndDeath() != squadLeader.getYearBirthAndDeath()) {
            newSquadLeader.setYearBirthAndDeath(squadLeader.getYearBirthAndDeath());
        }

        if(squadLeader.getTitle() != null && newSquadLeader.getTitle() != squadLeader.getTitle()) {
            newSquadLeader.setTitle(squadLeader.getTitle());
        }

        if(squadLeader.getGender() != null && newSquadLeader.getGender() != squadLeader.getGender()) {
            newSquadLeader.setGender(squadLeader.getGender());
        }

        return squadLeaderRepository.save(newSquadLeader);
    }
}
