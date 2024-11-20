package com.testament.veltahleon.repositories.dogma;

import com.testament.veltahleon.model.entities.dogma.Prophet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProphetRepository extends JpaRepository<Prophet, Long> {
    Page<Prophet> findByReligion_Name(String religionName, Pageable pageable);
    Prophet findByName(String name);
    Boolean deleteByName(String name);
    long countByReligion_Name(String name);
}
