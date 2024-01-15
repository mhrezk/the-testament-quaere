package com.testament.veltahleon.services.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Fae;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.FaeDAO;
import com.testament.veltahleon.services.dao.ifc.religion.mythology.FaeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class FaeServiceImpl implements FaeService {

    @Autowired
    private FaeDAO faeDAO;

    @Override
    public Collection<Fae> getFaes() {
        return faeDAO.getFaes();
    }

    @Override
    public Fae getFaeByID(Long id) {
        return faeDAO.getFaeByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteFaeByID(Long id) {
        return faeDAO.deleteFaeByID(id);
    }

    @Override
    @Transactional
    public Fae saveFae(Fae fae) {
        return faeDAO.saveFae(fae);
    }

    @Override
    @Transactional
    public Fae updateFae(Fae fae) {
        return faeDAO.updateFae(fae);
    }
}
