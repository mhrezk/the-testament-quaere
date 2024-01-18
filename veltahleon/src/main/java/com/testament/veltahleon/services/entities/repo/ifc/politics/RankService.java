package com.testament.veltahleon.services.entities.repo.ifc.politics;

import com.testament.veltahleon.model.entities.politics.Rank;

import java.util.Collection;

public interface RankService {

    Collection<Rank> getRanks();
    Rank getRankByID(Long id);
    Boolean deleteRankByID(Long id);
    Rank saveRank(Rank rank);
    Rank updateRank(Rank rank);
}
