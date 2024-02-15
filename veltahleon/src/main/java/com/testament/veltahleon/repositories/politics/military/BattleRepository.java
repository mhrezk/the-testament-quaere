package com.testament.veltahleon.repositories.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

    Battle findByName(String name);
    Collection<Battle> findByArmies_Leader_Name(String name);
    Collection<Battle> findByNationalLeaders_Name(String name);
}
