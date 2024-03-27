package com.testament.veltahleon.repositories.dogma;

import com.testament.veltahleon.model.entities.dogma.Prophet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProphetRepository extends JpaRepository<Prophet, Long> {

    Prophet findByName(String name);
    Boolean deleteByName(String name);
}
