package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion;

import com.testament.veltahleon.model.entities.religion.Prophet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProphetRepository extends JpaRepository<Prophet, Long> {

    Prophet findByName(String name);
    Boolean deleteByName(String name);
}
