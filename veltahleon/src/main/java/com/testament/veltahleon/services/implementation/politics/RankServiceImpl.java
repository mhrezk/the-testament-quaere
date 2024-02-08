package com.testament.veltahleon.services.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Rank;
import com.testament.veltahleon.repositories.politics.RankRepository;
import com.testament.veltahleon.services.ifc.politics.RankService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RankServiceImpl implements RankService {

    @Autowired
    private RankRepository rankRepository;

    @Override
    public Collection<Rank> getRanks() {
        return rankRepository.findAll();
    }

    @Override
    public Collection<Rank> getRanksByRankType(String rankType) {
        return rankRepository.findByRankType(rankType);
    }

    @Override
    public Rank getRankByID(Long id) {
        return rankRepository.findById(id).orElseThrow();
    }

    @Override
    public Rank getRankByRankName(String name) {
        return rankRepository.findByName(name);
    }

    @Override
    public Rank getRankByRankAbove(String rank) {
        return rankRepository.findByRankAbove(rank);
    }

    @Override
    public Rank getRankByRankBelow(String rank) {
        return rankRepository.findByRankBelow(rank);
    }

    @Override
    public Boolean deleteRankByID(Long id) {
        rankRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Rank saveRank(Rank rank) {
        return rankRepository.save(rank);
    }

    @Override
    public Rank updateRank(Long id, Rank rank) {
        Rank newRank = rankRepository.findById(id).orElseThrow();

        if(rank.getName() != null && newRank.getName() != rank.getName()) {
            newRank.setName(rank.getName());
        }

        if(rank.getNation() != null && newRank.getNation() != rank.getNation()) {
            newRank.setNation(rank.getNation());
        }

        if(rank.getDescription() != null && newRank.getDescription() != rank.getDescription()) {
            newRank.setDescription(rank.getDescription());
        }

        if(rank.getRankAbove() != null && newRank.getRankAbove() != rank.getRankAbove()) {
            newRank.setRankAbove(rank.getRankAbove());
        }

        if(rank.getRankBelow() != null && newRank.getRankBelow() != rank.getRankBelow()) {
            newRank.setRankBelow(rank.getRankBelow());
        }

        if(rank.getRankType() != null && newRank.getRankType() != rank.getRankType()) {
            newRank.setRankType(rank.getRankType());
        }

        if(rank.getImageURL() != null && newRank.getImageURL() != rank.getImageURL()) {
            newRank.setImageURL(rank.getImageURL());
        }

        return rankRepository.save(newRank);
    }
}
