package com.testament.veltahleon.repositories.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Rank;
import com.testament.veltahleon.repositories.dao.ifc.politics.RankDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class RankDAOImpl implements RankDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Collection<Rank> getRanks() {
        TypedQuery<Rank> query = entityManager.createQuery("FROM Rank", Rank.class);
        return query.getResultList();
    }

    @Override
    public Rank getRankByID(Long id) {
        return entityManager.find(Rank.class, id);
    }

    @Override
    @Transactional
    public Boolean deleteRankByID(Long id) {
        Rank rank = entityManager.find(Rank.class, id);
        entityManager.remove(rank);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Rank saveRank(Rank rank) {
        entityManager.persist(rank);
        return rank;
    }

    @Override
    @Transactional
    public Rank updateRank(Rank rank) {
        return entityManager.merge(rank);
    }
}
