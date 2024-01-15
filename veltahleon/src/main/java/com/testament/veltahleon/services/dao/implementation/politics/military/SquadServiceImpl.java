package com.testament.veltahleon.services.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Squad;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.SquadDAO;
import com.testament.veltahleon.services.dao.ifc.politics.military.SquadService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class SquadServiceImpl implements SquadService {

    @Autowired
    private SquadDAO squadDAO;

    @Override
    public Collection<Squad> getSquads() {
        return squadDAO.getSquads();
    }

    @Override
    public Squad getSquadByID(Long id) {
        return squadDAO.getSquadByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteSquadByID(Long id) {
        return squadDAO.deleteSquadByID(id);
    }

    @Override
    @Transactional
    public Squad saveSquad(Squad squad) {
        return squadDAO.saveSquad(squad);
    }

    @Override
    @Transactional
    public Squad updateSquad(Squad squad) {
        return squadDAO.updateSquad(squad);
    }
}
