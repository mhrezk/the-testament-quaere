package com.testament.veltahleon.services.entities.repo.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Rank;

import java.util.Collection;

public interface RankService {

    Collection<Rank> getRanks();
    Collection<Rank> getRanksByRankType(String rankType);
    Rank getRankByID(Long id);
    Rank getRankByRankName(String name);
    Rank getRankByRankAbove(String rank);
    Rank getRankByRankBelow(String rank);
    Boolean deleteRankByID(Long id);
    Rank saveRank(Rank rank);
    Rank updateRank(Long id, Rank rank);
}
