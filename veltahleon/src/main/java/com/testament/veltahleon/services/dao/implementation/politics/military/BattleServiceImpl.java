package com.testament.veltahleon.services.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.BattleDAO;
import com.testament.veltahleon.services.dao.ifc.politics.military.BattleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class BattleServiceImpl implements BattleService {

    @Autowired
    private BattleDAO battleDAO;

    @Override
    public Collection<Battle> getBattles() {
        return battleDAO.getBattles();
    }

    @Override
    public Battle getBattleByID(Long id) {
        return battleDAO.getBattleByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteBattleByID(Long id) {
        return battleDAO.deleteBattleByID(id);
    }

    @Override
    @Transactional
    public Battle saveBattle(Battle battle) {
        return battleDAO.saveBattle(battle);
    }

    @Override
    @Transactional
    public Battle updateBattle(Battle battle) {
        return battleDAO.updateBattle(battle);
    }
}
