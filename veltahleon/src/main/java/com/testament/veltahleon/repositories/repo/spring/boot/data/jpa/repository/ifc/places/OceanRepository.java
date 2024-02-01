package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.places;

import com.testament.veltahleon.model.entities.places.Ocean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OceanRepository extends JpaRepository<Ocean, Long> {

    Ocean findByName(String name);
}
