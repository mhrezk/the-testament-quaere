package com.testament.veltahleon.services.dao.implementation.religion.mythology;

import com.testament.veltahleon.model.entities.religion.mythology.Angel;
import com.testament.veltahleon.repositories.dao.ifc.religion.mythology.AngelDAO;
import com.testament.veltahleon.services.dao.ifc.religion.mythology.AngelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class AngelServiceImpl implements AngelService {

    @Autowired
    private AngelDAO angelDAO;

    @Override
    public Collection<Angel> getAngels() {
        return angelDAO.getAngels();
    }

    @Override
    public Angel getAngelByID(Long id) {
        return angelDAO.getAngelByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteAngelByID(Long id) {
        return angelDAO.deleteAngelByID(id);
    }

    @Override
    @Transactional
    public Angel saveAngel(Angel angel) {
        return angelDAO.saveAngel(angel);
    }

    @Override
    @Transactional
    public Angel updateAngel(Angel angel) {
        return angelDAO.updateAngel(angel);
    }
}
