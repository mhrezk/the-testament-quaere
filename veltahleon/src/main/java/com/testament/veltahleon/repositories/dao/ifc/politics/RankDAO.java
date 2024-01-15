package com.testament.veltahleon.repositories.dao.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Rank;

import java.util.Collection;

public interface RankDAO {

    Collection<Rank> getRanks();
    Rank getRankByID(Long id);
    Boolean deleteRankByID(Long id);
    Rank saveRank(Rank rank);
    Rank updateRank(Rank rank);
}