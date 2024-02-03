package com.testament.veltahleon.services.entities.repo.implementation.politics;

import com.testament.veltahleon.model.entities.politics.NationLeader;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics.NationLeaderRepository;
import com.testament.veltahleon.services.entities.repo.ifc.politics.NationLeaderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class NationLeaderServiceImpl implements NationLeaderService {

    @Autowired
    private NationLeaderRepository nationLeaderRepository;


    @Override
    public Collection<NationLeader> getNationLeadersWithPagination(int pageNumber, int numberOfRecords) {
        return nationLeaderRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<NationLeader> getNationLeaders() {
        return nationLeaderRepository.findAll();
    }

    @Override
    public NationLeader getNationLeaderByID(Long id) {
        return nationLeaderRepository.findById(id).orElseThrow();
    }

    @Override
    public NationLeader getNationLeaderByName(String name) {
        return nationLeaderRepository.findByName(name);
    }

    @Override
    public Boolean deleteNationLeaderByID(Long id) {
        nationLeaderRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public NationLeader saveNationLeader(NationLeader nationLeader) {
        return nationLeaderRepository.save(nationLeader);
    }

    @Override
    public NationLeader updateNationLeader(Long id, NationLeader nationLeader) {
        NationLeader newNationLeader = nationLeaderRepository.findById(id).orElseThrow();

        if(nationLeader.getName() != null && newNationLeader.getName() != nationLeader.getName()) {
            newNationLeader.setName(nationLeader.getName());
        }

        if(nationLeader.getNation() != null && newNationLeader.getNation() != nationLeader.getNation()) {
            newNationLeader.setNation(nationLeader.getNation());
        }



        return nationLeaderRepository.save(newNationLeader);
    }
}
