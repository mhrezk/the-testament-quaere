package com.testament.veltahleon.services.entities.repo.implementation.divination;

import com.testament.veltahleon.model.entities.divination.Tarot;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.divination.TarotRepository;
import com.testament.veltahleon.services.entities.repo.ifc.divination.TarotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TarotServiceImpl implements TarotService {

    @Autowired
    private TarotRepository tarotRepository;

    @Override
    public Collection<Tarot> getTarotsWithPagination(int pageNumber, int numberOfRecords) {
        return tarotRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Tarot> getTarots() {
        return tarotRepository.findAll();
    }

    @Override
    public Tarot getTarotByID(Long id) {
        return tarotRepository.findById(id).orElseThrow();
    }

    @Override
    public Boolean deleteTarotByID(Long id) {
        tarotRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Tarot saveTarot(Tarot tarot) {
        return tarotRepository.save(tarot);
    }

    @Override
    public Tarot updateTarot(Long id, Tarot tarot) {
        Tarot newTarot = tarotRepository.findById(id).orElseThrow();

        if(tarot.getName() != null && newTarot.getName() != tarot.getName()) {
            newTarot.setName(tarot.getName());
        }

        if(tarot.getDescription() != null && newTarot.getDescription() != tarot.getDescription()) {
            newTarot.setDescription(tarot.getDescription());
        }

        if(tarot.getImageURL() != null && newTarot.getImageURL() != tarot.getImageURL()) {
            newTarot.setImageURL(tarot.getImageURL());
        }


        return tarotRepository.save(newTarot);
    }
}
