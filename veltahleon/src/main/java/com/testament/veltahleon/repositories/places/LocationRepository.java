package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByName(String name);
    Collection<Location> findByNation_Name(String name);
}
