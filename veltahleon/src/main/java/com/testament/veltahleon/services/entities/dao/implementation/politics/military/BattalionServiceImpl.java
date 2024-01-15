package com.testament.veltahleon.services.entities.dao.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;
import com.testament.veltahleon.repositories.dao.ifc.politics.military.BattalionDAO;
import com.testament.veltahleon.services.entities.dao.ifc.politics.military.BattalionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class BattalionServiceImpl implements BattalionService {

    @Autowired
    private BattalionDAO battalionDAO;

    @Override
    public Collection<Battalion> getBattalions() {
        return battalionDAO.getBattalions();
    }

    @Override
    public Battalion getBattalionByID(Long id) {
        return battalionDAO.getBattalionByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteBattalionByID(Long id) {
        return battalionDAO.deleteBattalionByID(id);
    }

    @Override
    @Transactional
    public Battalion saveBattalion(Battalion battalion) {
        return battalionDAO.saveBattalion(battalion);
    }

    @Override
    @Transactional
    public Battalion updateBattalion(Battalion battalion) {
        return battalionDAO.updateBattalion(battalion);
    }
}
