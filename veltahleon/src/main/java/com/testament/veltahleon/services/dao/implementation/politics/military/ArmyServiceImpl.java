package com.testament.veltahleon.services.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Army;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.ArmyDAO;
import com.testament.veltahleon.services.dao.ifc.politics.military.ArmyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArmyServiceImpl implements ArmyService {

    @Autowired
    private ArmyDAO armyDAO;

    @Override
    public Collection<Army> getArmies() {
        return armyDAO.getArmies();
    }

    @Override
    public Army getArmyByID(Long id) {
        return armyDAO.getArmyByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteArmyByID(Long id) {
        return armyDAO.deleteArmyByID(id);
    }

    @Override
    @Transactional
    public Army saveArmy(Army army) {
        return armyDAO.saveArmy(army);
    }

    @Override
    @Transactional
    public Army updateArmy(Army army) {
        return armyDAO.updateArmy(army);
    }
}
