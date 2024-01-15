package com.testament.veltahleon.services.entities.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.MilitaryLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.MilitaryLeaderDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.military.MilitaryLeaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class MilitaryLeaderServiceImpl implements MilitaryLeaderService {

    @Autowired
    private MilitaryLeaderDAO militaryLeaderDAO;

    @Override
    public Collection<MilitaryLeader> getMilitaryLeaders() {
        return militaryLeaderDAO.getMilitaryLeaders();
    }

    @Override
    public MilitaryLeader getMilitaryLeaderByID(Long id) {
        return militaryLeaderDAO.getMilitaryLeaderByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteMilitaryLeaderByID(Long id) {
        return militaryLeaderDAO.deleteMilitaryLeaderByID(id);
    }

    @Override
    @Transactional
    public MilitaryLeader saveMilitaryLeader(MilitaryLeader militaryLeader) {
        return militaryLeaderDAO.saveMilitaryLeader(militaryLeader);
    }

    @Override
    @Transactional
    public MilitaryLeader updateMilitaryLeader(MilitaryLeader militaryLeader) {
        return militaryLeaderDAO.updateMilitaryLeader(militaryLeader);
    }
}
