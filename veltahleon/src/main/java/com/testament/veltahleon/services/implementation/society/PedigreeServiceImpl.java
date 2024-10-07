package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Pedigree;
import com.testament.veltahleon.repositories.society.PedigreeRepository;
import com.testament.veltahleon.services.ifc.society.PedigreeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PedigreeServiceImpl implements PedigreeService {

    @Autowired
    private PedigreeRepository pedigreeRepository;

    @Override
    public Collection<Pedigree> getPedigreesWithPagination(int pageNumber, int numberOfRecords) {
        return pedigreeRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Pedigree> getPedigrees() {
        return pedigreeRepository.findAll();
    }

    @Override
    public Collection<Pedigree> getPedigreesByGender(String gender) {
        return pedigreeRepository.findByGender(gender);
    }

    @Override
    public Pedigree getPedigreeByID(Long id) {
        return pedigreeRepository.findById(id).orElseThrow();
    }

    @Override
    public Pedigree getPedigreeByMotherID(Long motherID) {
        return pedigreeRepository.findByMid(motherID);
    }

    @Override
    public Pedigree getPedigreeByFatherID(Long fatherID) {
        return pedigreeRepository.findByFid(fatherID);
    }

    @Override
    public Pedigree getPedigreeBySpousalIDs(List<Long> spousalIDs) {
        return pedigreeRepository.findByPids(spousalIDs);
    }

    @Override
    public Pedigree getPedigreeByLineageName(String lineageName) {
        return pedigreeRepository.findByLineageName(lineageName);
    }

    @Override
    public Pedigree getPedigreeByFirstName(String firstName) {
        return pedigreeRepository.findByFirstName(firstName);
    }

    @Override
    public Boolean deletePedigreeByID(Long id) {
        pedigreeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Pedigree savePedigree(Pedigree pedigree) {
        return pedigreeRepository.save(pedigree);
    }

    @Override
    public Pedigree updatePedigree(Long id, Pedigree pedigree) {
        Pedigree newPedigree = pedigreeRepository.findById(id).orElseThrow();

        if(pedigree.getFirstName() != null && newPedigree.getFirstName() != pedigree.getFirstName()) {
            newPedigree.setFirstName(pedigree.getFirstName());
        }

        if(pedigree.getSecondName() != null && newPedigree.getSecondName() != pedigree.getSecondName()) {
            newPedigree.setSecondName(pedigree.getSecondName());
        }

        if(pedigree.getLineageName() != null && newPedigree.getLineageName() != pedigree.getLineageName()) {
            newPedigree.setLineageName(pedigree.getLineageName());
        }

        if(pedigree.getMid() != null && newPedigree.getMid() != pedigree.getMid()) {
            newPedigree.setMid(pedigree.getMid());
        }

        if(pedigree.getFid() != null && newPedigree.getFid() != pedigree.getFid()) {
            newPedigree.setFid(pedigree.getFid());
        }

        if(pedigree.getPids() != null && newPedigree.getPids() != pedigree.getPids()) {
            newPedigree.setPids(pedigree.getPids());
        }

        if(pedigree.getGender() != null && newPedigree.getGender() != pedigree.getGender()) {
            newPedigree.setGender(pedigree.getGender());
        }

        if(pedigree.getImageURL() != null && newPedigree.getImageURL() != pedigree.getImageURL()) {
            newPedigree.setImageURL(pedigree.getImageURL());
        }

        return pedigreeRepository.save(newPedigree);
    }

    @Override
    public Pedigree modifyPedigree(Long id, Pedigree pedigree) {
        Pedigree newPedigree = pedigreeRepository.findById(id).orElseThrow();
        newPedigree.setFirstName(pedigree.getFirstName());
        newPedigree.setSecondName(pedigree.getSecondName());
        newPedigree.setLineageName(pedigree.getLineageName());
        newPedigree.setMid(pedigree.getMid());
        newPedigree.setFid(pedigree.getFid());
        newPedigree.setPids(pedigree.getPids());
        newPedigree.setGender(pedigree.getGender());
        newPedigree.setImageURL(pedigree.getImageURL());
        return pedigreeRepository.save(newPedigree);
    }
}
