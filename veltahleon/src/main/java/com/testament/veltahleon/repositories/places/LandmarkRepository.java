package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    Landmark findByName(String name);
    Collection<Landmark> findByNation_Name(String name);
}
