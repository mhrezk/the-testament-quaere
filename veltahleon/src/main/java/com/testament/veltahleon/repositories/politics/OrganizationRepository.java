package com.testament.veltahleon.repositories.politics;

import com.testament.veltahleon.model.entities.politics.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByName(String name);

    @Modifying
    @Query("update Organization o set o.organizationSize = :number where o.id = :id")
    void updateOrganizationSize(@Param("number") Integer number, @Param("id") Long id);
}
