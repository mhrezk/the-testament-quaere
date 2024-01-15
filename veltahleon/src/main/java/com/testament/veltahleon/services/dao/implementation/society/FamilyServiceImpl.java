package com.testament.veltahleon.services.dao.implementation.society;

import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.repositories.dao.ifc.society.FamilyDAO;
import com.testament.veltahleon.services.dao.ifc.society.FamilyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyDAO familyDAO;

    @Override
    public Collection<Family> getFamilies() {
        return familyDAO.getFamilies();
    }

    @Override
    public Family getFamilyByID(Long id) {
        return familyDAO.getFamilyByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteFamilyByID(Long id) {
        return familyDAO.deleteFamilyByID(id);
    }

    @Override
    @Transactional
    public Family saveFamily(Family family) {
        return familyDAO.saveFamily(family);
    }

    @Override
    @Transactional
    public Family updateFamily(Family family) {
        return familyDAO.updateFamily(family);
    }
}
