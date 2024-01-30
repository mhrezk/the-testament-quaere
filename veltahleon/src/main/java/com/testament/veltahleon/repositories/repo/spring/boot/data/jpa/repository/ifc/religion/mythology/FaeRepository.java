package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Fae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaeRepository extends JpaRepository<Fae, Long> {

    Fae findByName(String name);
}
