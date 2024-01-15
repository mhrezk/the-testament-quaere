package com.testament.veltahleon.services.dao.implementation.divination;

import com.testament.veltahleon.model.entities.divination.Tarot;
import com.testament.veltahleon.repositories.dao.ifc.divination.TarotDAO;
import com.testament.veltahleon.services.dao.ifc.divination.TarotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TarotServiceImpl implements TarotService {

    @Autowired
    private TarotDAO tarotDAO;

    @Override
    public Collection<Tarot> getTarots() {
        return tarotDAO.getTarots();
    }

    @Override
    public Tarot getTarotByID(Long id) {
        return tarotDAO.getTarotByID(id);
    }

    @Override
    @Transactional
    public Boolean deleteTarotByID(Long id) {
        return tarotDAO.deleteTarotByID(id);
    }

    @Override
    @Transactional
    public Tarot saveTarot(Tarot tarot) {
        return tarotDAO.saveTarot(tarot);
    }

    @Override
    @Transactional
    public Tarot updateTarot(Tarot tarot) {
        return tarotDAO.updateTarot(tarot);
    }
}
