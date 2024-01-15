package com.testament.veltahleon.services.dao.implementation.politics;

import com.testament.veltahleon.model.entities.politics.Pundit;
import com.testament.veltahleon.repositories.dao.ifc.politics.PunditDAO;
import com.testament.veltahleon.services.dao.ifc.politics.PunditService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class PunditServiceImpl implements PunditService {

    @Autowired
    private PunditDAO punditDAO;

    @Override
    public Collection<Pundit> getPundits() {
        return punditDAO.getPundits();
    }

    @Override
    public Pundit getPunditByID(Long id) {
        return punditDAO.getPunditByID(id);
    }

    @Override
    @Transactional
    public Boolean deletePunditByID(Long id) {
        return punditDAO.deletePunditByID(id);
    }

    @Override
    @Transactional
    public Pundit savePundit(Pundit pundit) {
        return punditDAO.savePundit(pundit);
    }

    @Override
    @Transactional
    public Pundit updatePundit(Pundit pundit) {
        return punditDAO.updatePundit(pundit);
    }
}
