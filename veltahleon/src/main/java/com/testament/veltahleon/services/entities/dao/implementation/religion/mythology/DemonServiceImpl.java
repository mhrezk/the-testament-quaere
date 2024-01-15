package com.testament.veltahleon.services.entities.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Demon;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.DemonDAO;
import com.testament.veltahleon.services.entities.dao.ifc.religion.mythology.DemonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemonServiceImpl implements DemonService {

    @Autowired
    private DemonDAO demonDAO;

    @Override
    public Collection<Demon> getDemons() {
        return demonDAO.getDemons();
    }

    @Override
    public Demon getDemonByID(Long id) {
        return demonDAO.getDemonByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteDemonByID(Long id) {
        return demonDAO.deleteDemonByID(id);
    }

    @Override
    @Transactional
    public Demon saveDemon(Demon demon) {
        return demonDAO.saveDemon(demon);
    }

    @Override
    @Transactional
    public Demon updateDemon(Demon demon) {
        return demonDAO.updateDemon(demon);
    }
}
