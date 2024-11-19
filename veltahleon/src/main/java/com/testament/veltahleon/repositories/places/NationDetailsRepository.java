package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.NationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NationDetailsRepository extends JpaRepository<NationDetails, Long> {
    Collection<NationDetails> findByLeaderFirstName(String firstName);
    NationDetails findByLeaderFirstNameAndLeaderSecondName(String firstName, String secondName);
    NationDetails findByNation_Name(String nationName);
    //NationDetails findByCapital_Name(String capitalName);
    long countByNation_Name(String name);
    long deleteByNation_Name(String name);
}
