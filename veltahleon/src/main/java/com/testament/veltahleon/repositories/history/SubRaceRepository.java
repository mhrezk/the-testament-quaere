package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.SubRace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubRaceRepository extends JpaRepository<SubRace, Long> {
    Collection<SubRace> findByRace_Name(String name);
    Page<SubRace> findByRace_Name(String name, Pageable pageable);
    SubRace findByName(String name);
    boolean existsSubRaceByName(String name);
    long countByName(String name);
    long countByRace_Name(String name);
}
