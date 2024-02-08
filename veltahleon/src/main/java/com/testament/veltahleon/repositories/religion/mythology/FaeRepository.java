package com.testament.veltahleon.repositories.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Fae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FaeRepository extends JpaRepository<Fae, Long> {

    Fae findByName(String name);
    Collection<Fae> findByRace_Name(String name);
}
