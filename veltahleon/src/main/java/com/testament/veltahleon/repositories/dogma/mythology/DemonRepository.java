package com.testament.veltahleon.repositories.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Demon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemonRepository extends JpaRepository<Demon, Long> {

    Page<Demon> findByReligion_Name(String religionName, Pageable pageable);
    Demon findByName(String name);
    long countByReligion_Name(String name);
    long countByName(String name);
}
