package com.testament.veltahleon.services.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;

import java.util.Collection;

public interface BattleService {

    Collection<Battle> getBattlesWithPagination(int pageNumber, int numberOfRecords);
    Collection<Battle> getBattles();
    Battle getBattleByID(Long id);
    Battle getBattleByName(String name);
    Boolean deleteBattleByID(Long id);
    Battle saveBattle(Battle battle);
    Battle updateBattle(Long id, Battle battle);
}
