package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long> {

    Capital findByName(String name);
    Capital findByNation_Name(String name);
}
