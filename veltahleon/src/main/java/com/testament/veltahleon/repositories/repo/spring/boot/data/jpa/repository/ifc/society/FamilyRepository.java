package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.society;

import com.testament.veltahleon.model.entities.society.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {

    Family findByPerson_Name(String name);
    Collection<Family> findByLineage(String lineage);
}
