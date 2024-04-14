package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {

    Race findByName(String name);
    long countByName(String name);
}
