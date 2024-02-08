package com.testament.veltahleon.repositories.politics;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.Pundit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PunditRepository extends JpaRepository<Pundit, Long> {

    Pundit findByName(String name);
    Pundit findByOrganization_Name(String name);
}
