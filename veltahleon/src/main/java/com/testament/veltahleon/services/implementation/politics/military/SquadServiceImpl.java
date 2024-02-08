package com.testament.veltahleon.services.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;
import com.testament.veltahleon.repositories.politics.military.SquadRepository;
import com.testament.veltahleon.services.ifc.politics.military.SquadService;
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
public class SquadServiceImpl implements SquadService {

    @Autowired
    private SquadRepository squadRepository;

    @Override
    public Collection<Squad> getSquadsWithPagination(int pageNumber, int numberOfRecords) {
        return squadRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Squad> getSquads() {
        return squadRepository.findAll();
    }

    @Override
    public Squad getSquadByID(Long id) {
        return squadRepository.findById(id).orElseThrow();
    }

    @Override
    public Squad getSquadBySquadLeader(String name) {
        return squadRepository.findByLeader_Name(name);
    }

    @Override
    public Boolean deleteSquadByID(Long id) {
        squadRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Squad saveSquad(Squad squad) {
        return squadRepository.save(squad);
    }

    @Override
    public Squad updateSquad(Long id, Squad squad) {
        Squad newSquad = squadRepository.findById(id).orElseThrow();

        if(squad.getLeader() != null && newSquad.getLeader() != squad.getLeader()) {
            newSquad.setLeader(squad.getLeader());
        }

        if(squad.getUnit() != null && newSquad.getUnit() != squad.getUnit()) {
            newSquad.setUnit(squad.getUnit());
        }

        if(squad.getDescription() != null && newSquad.getDescription() != squad.getDescription()) {
            newSquad.setDescription(squad.getDescription());
        }

        if(squad.getUnitNumber() != null && newSquad.getUnitNumber() != squad.getUnitNumber()) {
            newSquad.setUnitNumber(squad.getUnitNumber());
        }

        return squadRepository.save(newSquad);
    }
}
