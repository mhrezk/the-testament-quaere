package com.testament.veltahleon.repositories.history;

import com.testament.veltahleon.model.entities.history.Race;
import com.testament.veltahleon.model.entities.history.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RaceRepository extends JpaRepository<Race, Long> {

    Race findByName(String name);
}
