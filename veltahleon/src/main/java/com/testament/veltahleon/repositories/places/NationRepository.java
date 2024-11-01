package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {

    Nation findByName(String name);
    Collection<Nation> findByType(String nationType);
    Collection<Nation> findByGovernanceType(String governanceType);
    //long countByProvinces_Name(String provincialName);
    long countByName(String name);
}
