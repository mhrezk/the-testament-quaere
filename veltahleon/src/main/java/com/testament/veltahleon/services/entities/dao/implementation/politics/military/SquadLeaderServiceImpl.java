package com.testament.veltahleon.services.entities.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.SquadLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.SquadLeaderDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.military.SquadLeaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class SquadLeaderServiceImpl implements SquadLeaderService {

    @Autowired
    private SquadLeaderDAO squadLeaderDAO;

    @Override
    public Collection<SquadLeader> getSquadLeaders() {
        return squadLeaderDAO.getSquadLeaders();
    }

    @Override
    public SquadLeader getSquadLeaderByID(Long id) {
        return squadLeaderDAO.getSquadLeaderByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteSquadLeaderByID(Long id) {
        return squadLeaderDAO.deleteSquadLeaderByID(id);
    }

    @Override
    @Transactional
    public SquadLeader saveSquadLeader(SquadLeader squadLeader) {
        return squadLeaderDAO.saveSquadLeader(squadLeader);
    }

    @Override
    @Transactional
    public SquadLeader updateSquadLeader(SquadLeader squadLeader) {
        return squadLeaderDAO.updateSquadLeader(squadLeader);
    }
}
