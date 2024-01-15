package com.testament.veltahleon.services.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.NationLeaderDAO;
import com.testament.veltahleon.services.dao.ifc.politics.NationLeaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class NationLeaderServiceImpl implements NationLeaderService {

    @Autowired
    private NationLeaderDAO nationLeaderDAO;

    @Override
    public Collection<NationLeader> getNationLeaders() {
        return nationLeaderDAO.getNationLeaders();
    }

    @Override
    public NationLeader getNationLeaderByID(Long id) {
        return nationLeaderDAO.getNationLeaderByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteNationLeaderByID(Long id) {
        return nationLeaderDAO.deleteNationLeaderByID(id);
    }

    @Override
    @Transactional
    public NationLeader saveNationLeader(NationLeader nationLeader) {
        return nationLeaderDAO.saveNationLeader(nationLeader);
    }

    @Override
    @Transactional
    public NationLeader updateNationLeader(NationLeader nationLeader) {
        return nationLeaderDAO.updateNationLeader(nationLeader);
    }
}
