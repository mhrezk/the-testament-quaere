package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {

    Continent findByName(String name);
}
