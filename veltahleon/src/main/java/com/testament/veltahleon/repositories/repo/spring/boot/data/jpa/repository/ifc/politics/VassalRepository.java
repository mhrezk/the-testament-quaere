package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VassalRepository extends JpaRepository<Vassal, Long> {

    Vassal findByName(String name);
    Collection<Vassal> findByNationLeader_Name(String name);
}
