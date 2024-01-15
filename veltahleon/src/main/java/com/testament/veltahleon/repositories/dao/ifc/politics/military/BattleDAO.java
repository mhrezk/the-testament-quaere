package com.testament.veltahleon.repositories.dao.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;

import java.util.Collection;

public interface BattleDAO {

    Collection<Battle> getBattles();
    Battle getBattleByID(Long id);
    Boolean deleteBattleByID(Long id);
    Battle saveBattle(Battle Battle);
    Battle updateBattle(Battle Battle);
}
