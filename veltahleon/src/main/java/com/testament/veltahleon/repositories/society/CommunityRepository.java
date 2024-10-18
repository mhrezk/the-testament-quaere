package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Community findByName(String name);
    long countByName(String name);

    //Custom Update Query
    @Modifying
    @Query("update Community c set c.communitySize = :number where c.id = :id")
    void updateCommunitySize(@Param("number") Integer number, @Param("id") Long id);
}
