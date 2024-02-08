package com.testament.veltahleon.repositories.religion;

import com.testament.veltahleon.model.entities.religion.Religion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {

    Religion findByName(String name);
}
