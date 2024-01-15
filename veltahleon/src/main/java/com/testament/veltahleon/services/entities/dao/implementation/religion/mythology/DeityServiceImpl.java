package com.testament.veltahleon.services.entities.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Deity;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.DeityDAO;
import com.testament.veltahleon.services.entities.dao.ifc.religion.mythology.DeityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeityServiceImpl implements DeityService {

    @Autowired
    private DeityDAO deityDAO;

    @Override
    public Collection<Deity> getDeities() {
        return deityDAO.getDeities();
    }

    @Override
    public Deity getDeityByID(Long id) {
        return deityDAO.getDeityByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteDeityByID(Long id) {
        return deityDAO.deleteDeityByID(id);
    }

    @Override
    @Transactional
    public Deity saveDeity(Deity deity) {
        return deityDAO.saveDeity(deity);
    }

    @Override
    @Transactional
    public Deity updateDeity(Deity deity) {
        return deityDAO.updateDeity(deity);
    }
}
