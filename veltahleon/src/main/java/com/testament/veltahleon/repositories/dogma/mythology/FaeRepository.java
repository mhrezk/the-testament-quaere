package com.testament.veltahleon.repositories.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Fae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FaeRepository extends JpaRepository<Fae, Long> {

    Fae findByName(String name);
    Collection<Fae> findByRace_Name(String name);
}
