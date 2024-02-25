package com.testament.veltahleon.repositories.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NationLeaderRepository extends JpaRepository<NationLeader, Long> {

    NationLeader findByName(String name);
    Collection<NationLeader> findByBattles_Name(String name);
}
