package com.testament.veltahleon.services.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;
import com.testament.veltahleon.repositories.politics.military.MilitaryLeaderRepository;
import com.testament.veltahleon.services.ifc.politics.military.MilitaryLeaderService;
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
public class MilitaryLeaderServiceImpl implements MilitaryLeaderService {

    @Autowired
    private MilitaryLeaderRepository militaryLeaderRepository;

    @Override
    public Collection<MilitaryLeader> getMilitaryLeadersWithPagination(int pageNumber, int numberOfRecords) {
        return militaryLeaderRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<MilitaryLeader> getMilitaryLeaders() {
        return militaryLeaderRepository.findAll();
    }

    @Override
    public Collection<MilitaryLeader> getMilitaryLeadersByNationName(String name) {
        return militaryLeaderRepository.findByNation_Name(name);
    }

    @Override
    public Collection<MilitaryLeader> getMilitaryLeadersByBattleName(String name) {
        return militaryLeaderRepository.findByBattles_Name(name);
    }

    @Override
    public MilitaryLeader getMilitaryLeaderByID(Long id) {
        return militaryLeaderRepository.findById(id).orElseThrow();
    }

    @Override
    public MilitaryLeader getMilitaryLeaderByName(String name) {
        return militaryLeaderRepository.findByName(name);
    }

    @Override
    public Boolean deleteMilitaryLeaderByID(Long id) {
        militaryLeaderRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public MilitaryLeader saveMilitaryLeader(MilitaryLeader militaryLeader) {
        return militaryLeaderRepository.save(militaryLeader);
    }

    @Override
    public MilitaryLeader updateMilitaryLeader(Long id, MilitaryLeader militaryLeader) {
        MilitaryLeader newMilitaryLeader = militaryLeaderRepository.findById(id).orElseThrow();

        if(militaryLeader.getName() != null && newMilitaryLeader.getName() != militaryLeader.getName()) {
            newMilitaryLeader.setName(militaryLeader.getName());
        }

        if(militaryLeader.getNation() != null && newMilitaryLeader.getNation() != militaryLeader.getNation()) {
            newMilitaryLeader.setNation(militaryLeader.getNation());
        }

        if(militaryLeader.getRace() != null && newMilitaryLeader.getRace() != militaryLeader.getRace()) {
            newMilitaryLeader.setRace(militaryLeader.getRace());
        }

        if(militaryLeader.getImageURL() != null && newMilitaryLeader.getImageURL() != militaryLeader.getImageURL()) {
            newMilitaryLeader.setImageURL(militaryLeader.getImageURL());
        }

        if(militaryLeader.getBiography() != null && newMilitaryLeader.getBiography() != militaryLeader.getBiography()) {
            newMilitaryLeader.setBiography(militaryLeader.getBiography());
        }

        if(militaryLeader.getReligion() != null && newMilitaryLeader.getReligion() != militaryLeader.getReligion()) {
            newMilitaryLeader.setReligion(militaryLeader.getReligion());
        }

        if(militaryLeader.getBattles() != null && newMilitaryLeader.getBattles() != militaryLeader.getBattles()) {
            newMilitaryLeader.setBattles(militaryLeader.getBattles());
        }

        if(militaryLeader.getFamily() != null && newMilitaryLeader.getFamily() != militaryLeader.getFamily()) {
            newMilitaryLeader.setFamily(militaryLeader.getFamily());
        }

        if(militaryLeader.getBirthYear() != null && newMilitaryLeader.getBirthYear() != militaryLeader.getBirthYear()) {
            newMilitaryLeader.setBirthYear(militaryLeader.getBirthYear());
        }

        if(militaryLeader.getDeathYear() != null && newMilitaryLeader.getDeathYear() != militaryLeader.getDeathYear()) {
            newMilitaryLeader.setDeathYear(militaryLeader.getDeathYear());
        }

        if(militaryLeader.getTitle() != null && newMilitaryLeader.getTitle() != militaryLeader.getTitle()) {
            newMilitaryLeader.setTitle(militaryLeader.getTitle());
        }

        if(militaryLeader.getGender() != null && newMilitaryLeader.getGender() != militaryLeader.getGender()) {
            newMilitaryLeader.setGender(militaryLeader.getGender());
        }
        
        return militaryLeaderRepository.save(newMilitaryLeader);
    }
}
