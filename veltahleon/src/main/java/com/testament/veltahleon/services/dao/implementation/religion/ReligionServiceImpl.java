package com.testament.veltahleon.services.dao.implementation.religion;

import com.testament.veltahleon.model.entities.religion.Religion;
import com.testament.veltahleon.repositories.dao.ifc.religion.ReligionDAO;
import com.testament.veltahleon.services.dao.ifc.religion.ReligionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReligionServiceImpl implements ReligionService {

    @Autowired
    private ReligionDAO religionDAO;

    @Override
    public Collection<Religion> getReligions() {
        return religionDAO.getReligions();
    }

    @Override
    public Religion getReligionByID(Long id) {
        return religionDAO.getReligionByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteReligionByID(Long id) {
        return religionDAO.deleteReligionByID(id);
    }

    @Override
    @Transactional
    public Religion saveReligion(Religion religion) {
        return religionDAO.saveReligion(religion);
    }

    @Override
    @Transactional
    public Religion updateReligion(Religion religion) {
        return religionDAO.updateReligion(religion);
    }
}
