package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.NationLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NationLeaderRepository extends JpaRepository<NationLeader, Long> {

    NationLeader findByName(String name);
}
