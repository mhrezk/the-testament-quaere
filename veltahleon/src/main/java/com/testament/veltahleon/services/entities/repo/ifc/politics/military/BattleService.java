package com.testament.veltahleon.services.entities.repo.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;

import java.util.Collection;

public interface BattleService {

    Collection<Battle> getBattles();
    Battle getBattleByID(Long id);
    Boolean deleteBattleByID(Long id);
    Battle saveBattle(Battle battle);
    Battle updateBattle(Battle battle);
}
