package com.testament.veltahleon.services.entities.repo.implementation.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;
import com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics.military.BattalionRepository;
import com.testament.veltahleon.services.entities.repo.ifc.politics.military.BattalionService;
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
public class BattalionServiceImpl implements BattalionService {

    @Autowired
    private BattalionRepository battalionRepository;

    @Override
    public Collection<Battalion> getBattalionsWithPagination(int pageNumber, int numberOfRecords) {
        return battalionRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Battalion> getBattalions() {
        return battalionRepository.findAll();
    }

    @Override
    public Battalion getBattalionByID(Long id) {
        return battalionRepository.findById(id).orElseThrow();
    }

    @Override
    public Battalion getBattalionByName(String name) {
        return battalionRepository.findByName(name);
    }

    @Override
    public Battalion getBattalionByLeaderName(String name) {
        return battalionRepository.findByLeader_Name(name);
    }

    @Override
    public Boolean deleteBattalionByID(Long id) {
        battalionRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Battalion saveBattalion(Battalion battalion) {
        return battalionRepository.save(battalion);
    }

    @Override
    public Battalion updateBattalion(Long id, Battalion battalion) {
        Battalion newBattalion = battalionRepository.findById(id).orElseThrow();

        if(battalion.getLeader() != null && newBattalion.getLeader() != battalion.getLeader()) {
            newBattalion.setLeader(battalion.getLeader());
        }

        if(battalion.getName() != null && newBattalion.getName() != battalion.getName()) {
            newBattalion.setName(battalion.getName());
        }

        if(battalion.getSquads() != null && newBattalion.getSquads() != battalion.getSquads()) {
            newBattalion.setSquads(battalion.getSquads());
        }

        return battalionRepository.save(newBattalion);
    }
}
