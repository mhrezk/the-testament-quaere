package com.testament.veltahleon.services.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.Ocean;
import com.testament.veltahleon.repositories.dao.ifc.places.OceanDAO;
import com.testament.veltahleon.services.dao.ifc.places.OceanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class OceanServiceImpl implements OceanService {

    @Autowired
    private OceanDAO oceanDAO;

    @Override
    public Collection<Ocean> getOceans() {
        return oceanDAO.getOceans();
    }

    @Override
    public Ocean getOceanByID(Long id) {
        return oceanDAO.getOceanByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteOceanByID(Long id) {
        return oceanDAO.deleteOceanByID(id);
    }

    @Override
    @Transactional
    public Ocean saveOcean(Ocean ocean) {
        return oceanDAO.saveOcean(ocean);
    }

    @Override
    @Transactional
    public Ocean updateOcean(Ocean ocean) {
        return oceanDAO.updateOcean(ocean);
    }
}
