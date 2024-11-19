package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {

    Collection<Nation> findByType(String nationType);
    Collection<Nation> findByGovernanceType(String governanceType);
    Nation findByName(String name);
    Nation findByCapital_Name(String capitalName);
    //long countByProvinces_Name(String provincialName);
    long countByName(String name);
    long deleteByName(String name);
}
