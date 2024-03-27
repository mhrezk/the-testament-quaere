package com.testament.veltahleon.repositories.dogma;

import com.testament.veltahleon.model.entities.dogma.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {

    Religion findByName(String name);
}
