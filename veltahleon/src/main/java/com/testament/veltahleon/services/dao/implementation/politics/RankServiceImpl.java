package com.testament.veltahleon.services.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Rank;
import com.testament.veltahleon.repositories.dao.ifc.politics.RankDAO;
import com.testament.veltahleon.services.dao.ifc.politics.RankService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDAO rankDAO;

    @Override
    public Collection<Rank> getRanks() {
        return rankDAO.getRanks();
    }

    @Override
    public Rank getRankByID(Long id) {
        return rankDAO.getRankByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteRankByID(Long id) {
        return rankDAO.deleteRankByID(id);
    }

    @Override
    @Transactional
    public Rank saveRank(Rank rank) {
        return rankDAO.saveRank(rank);
    }

    @Override
    @Transactional
    public Rank updateRank(Rank rank) {
        return rankDAO.updateRank(rank);
    }
}
