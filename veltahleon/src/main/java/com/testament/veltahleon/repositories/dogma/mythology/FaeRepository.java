package com.testament.veltahleon.repositories.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Fae;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FaeRepository extends JpaRepository<Fae, Long> {

    Page<Fae> findByReligion_Name(String religionName, Pageable pageable);
    Fae findByName(String name);
    Collection<Fae> findByRace_Name(String name);
    long countByReligion_Name(String name);
}
