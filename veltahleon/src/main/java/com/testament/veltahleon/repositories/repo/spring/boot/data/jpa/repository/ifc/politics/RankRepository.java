package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

    Rank findByName(String name);
    Rank findByRankAbove(String name);
    Rank findByRankBelow(String name);
    Collection<Rank> findByRankType(String rankType);
}
