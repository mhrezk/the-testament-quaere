package com.testament.veltahleon.services.entities.repo.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Army;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics.military.ArmyRepository;
import com.testament.veltahleon.services.entities.repo.ifc.politics.military.ArmyService;
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
public class ArmyServiceImpl implements ArmyService {

    @Autowired
    private ArmyRepository armyRepository;

    @Override
    public Collection<Army> getArmiesWithPagination(int pageNumber, int numberOfRecords) {
        return armyRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Army> getArmies() {
        return armyRepository.findAll();
    }

    @Override
    public Army getArmyByID(Long id) {
        return armyRepository.findById(id).orElseThrow();
    }

    @Override
    public Army getArmyByLeaderName(String name) {
        return armyRepository.findByLeader_Name(name);
    }

    @Override
    public Boolean deleteArmyByID(Long id) {
        armyRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Army saveArmy(Army army) {
        return armyRepository.save(army);
    }

    @Override
    public Army updateArmy(Long id, Army army) {
        Army newArmy = armyRepository.findById(id).orElseThrow();

        if(army.getLeader() != null && newArmy.getLeader() != army.getLeader()) {
            newArmy.setLeader(army.getLeader());
        }

        if(army.getBattalions() != null && newArmy.getBattalions() != army.getBattalions()) {
            newArmy.setBattalions(army.getBattalions());
        }

        return armyRepository.save(newArmy);
    }
}
