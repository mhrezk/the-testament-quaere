package com.testament.veltahleon.repositories.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MilitaryLeaderRepository extends JpaRepository<MilitaryLeader, Long> {

    MilitaryLeader findByName(String name);
    Collection<MilitaryLeader> findByNation_Name(String name);
    Collection<MilitaryLeader> findByBattles_Name(String name);
}
