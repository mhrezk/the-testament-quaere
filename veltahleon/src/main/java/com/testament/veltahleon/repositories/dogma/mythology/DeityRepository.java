package com.testament.veltahleon.repositories.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DeityRepository extends JpaRepository<Deity, Long> {
    Collection<Deity> findByReligion_Name(String religionName);
    Page<Deity> findByReligion_Name(String religionName, Pageable pageable);
    Deity findByName(String name);
    long countByReligion_Name(String name);
    long countByName(String name);
}
