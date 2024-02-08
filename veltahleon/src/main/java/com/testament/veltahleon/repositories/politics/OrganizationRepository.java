package com.testament.veltahleon.repositories.politics;

import com.testament.veltahleon.model.entities.politics.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByName(String name);
    Organization findByFounder_Name(String name);
}
