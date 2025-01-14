package com.testament.veltahleon.repositories.dogma.mythology;

import com.testament.veltahleon.model.entities.dogma.mythology.Angel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AngelRepository extends JpaRepository<Angel, Long> {

    Angel findByName(String name);
    Page<Angel> findByReligion_Name(String religionName, Pageable pageable);
    long countByReligion_Name(String name);
}
