package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Community findByName(String name);
    long countByName(String name);
}
