package com.testament.veltahleon.services.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import com.testament.veltahleon.repositories.politics.military.BattleRepository;
import com.testament.veltahleon.services.ifc.politics.military.BattleService;
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
public class BattleServiceImpl implements BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Override
    public Collection<Battle> getBattlesWithPagination(int pageNumber, int numberOfRecords) {
        return battleRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Battle> getBattles() {
        return battleRepository.findAll();
    }

    @Override
    public Battle getBattleByID(Long id) {
        return battleRepository.findById(id).orElseThrow();
    }

    @Override
    public Battle getBattleByName(String name) {
        return battleRepository.findByName(name);
    }

    @Override
    public Boolean deleteBattleByID(Long id) {
        battleRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Battle saveBattle(Battle battle) {
        return battleRepository.save(battle);
    }

    @Override
    public Battle updateBattle(Long id, Battle battle) {
        Battle newBattle = battleRepository.findById(id).orElseThrow();

        if(battle.getName() != null && newBattle.getName() != battle.getName()) {
            newBattle.setName(battle.getName());
        }

        if(battle.getBattleDescription() != null && newBattle.getBattleDescription() != battle.getBattleDescription()) {
            newBattle.setBattleDescription(battle.getBattleDescription());
        }

        if(battle.getArmies() != null && newBattle.getArmies() != battle.getArmies()) {
            newBattle.setArmies(battle.getArmies());
        }

        if(battle.getBattleYear() != null && newBattle.getBattleYear() != battle.getBattleYear()) {
            newBattle.setBattleYear(battle.getBattleYear());
        }

        if(battle.getBattleYear() != null && newBattle.getBattleYear() != battle.getBattleYear()) {
            newBattle.setBattleYear(battle.getBattleYear());
        }

        if(battle.getNationalLeaders() != null && newBattle.getNationalLeaders() != battle.getNationalLeaders()) {
            newBattle.setNationalLeaders(battle.getNationalLeaders());
        }

        return battleRepository.save(newBattle);
    }
}
