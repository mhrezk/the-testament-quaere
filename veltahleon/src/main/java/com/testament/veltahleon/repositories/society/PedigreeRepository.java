package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Pedigree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedigreeRepository extends JpaRepository<Pedigree, Long> {
    List<Pedigree> findByGender(String gender);
    Pedigree findByFirstName(String firstName);
    Pedigree findByLineageName(String lineageName);
    Pedigree findByMid(Long motherID);
    Pedigree findByFid(Long fatherID);
    Pedigree findByPids(List<Long> spousalIDs);
}
