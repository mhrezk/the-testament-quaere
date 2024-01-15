package com.testament.veltahleon.services.dao.implementation.places;

import com.testament.veltahleon.model.entities.places.NationType;
import com.testament.veltahleon.repositories.dao.ifc.places.NationTypeDAO;
import com.testament.veltahleon.services.dao.ifc.places.NationTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class NationTypeServiceImpl implements NationTypeService {

    @Autowired
    private NationTypeDAO nationTypeDAO;

    @Override
    public Collection<NationType> getNationTypes() {
        return nationTypeDAO.getNationTypes();
    }

    @Override
    public NationType getNationTypeByID(Long id) {
        return nationTypeDAO.getNationTypeByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteNationTypeByID(Long id) {
        return nationTypeDAO.deleteNationTypeByID(id);
    }

    @Override
    @Transactional
    public NationType saveNationType(NationType nationType) {
        return nationTypeDAO.saveNationType(nationType);
    }

    @Override
    @Transactional
    public NationType updateNationType(NationType nationType) {
        return nationTypeDAO.updateNationType(nationType);
    }
}
