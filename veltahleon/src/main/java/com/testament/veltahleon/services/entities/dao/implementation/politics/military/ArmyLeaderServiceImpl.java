package com.testament.veltahleon.services.entities.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.ArmyLeader;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.ArmyLeaderDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.military.ArmyLeaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArmyLeaderServiceImpl implements ArmyLeaderService {

    @Autowired
    private ArmyLeaderDAO armyLeaderDAO;

    @Override
    public Collection<ArmyLeader> getArmyLeaders() {
        return armyLeaderDAO.getArmyLeaders();
    }

    @Override
    public ArmyLeader getArmyLeaderByID(Long id) {
        return armyLeaderDAO.getArmyLeaderByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteArmyLeaderByID(Long id) {
        return armyLeaderDAO.deleteArmyLeaderByID(id);
    }

    @Override
    @Transactional
    public ArmyLeader saveArmyLeader(ArmyLeader armyLeader) {
        return armyLeaderDAO.saveArmyLeader(armyLeader);
    }

    @Override
    @Transactional
    public ArmyLeader updateArmyLeader(ArmyLeader armyLeader) {
        return armyLeaderDAO.updateArmyLeader(armyLeader);
    }
}
