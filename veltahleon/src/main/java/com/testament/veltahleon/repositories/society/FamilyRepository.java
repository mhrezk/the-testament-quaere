package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FamilyRepository extends JpaRepository<Family, String> {
    Collection<Family> findByCommunity_Name(String name);
    Collection<Family> findByFatherID(String fatherID);
    Collection<Family> findByMotherID(String motherID);
    Family findByFirstNameAndSecondName(String firstName, String secondName);
}
