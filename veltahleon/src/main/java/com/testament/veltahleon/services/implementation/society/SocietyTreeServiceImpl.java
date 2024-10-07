package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.SocietyTree;
import com.testament.veltahleon.repositories.society.SocietyTreeRepository;
import com.testament.veltahleon.services.ifc.society.SocietyTreeService;
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
public class SocietyTreeServiceImpl implements SocietyTreeService {

    @Autowired
    private SocietyTreeRepository societyTreeRepository;

    @Override
    public Collection<SocietyTree> getSocietyTreesWithPagination(int pageNumber, int numberOfRecords) {
        return societyTreeRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<SocietyTree> getSocietyTrees() {
        return societyTreeRepository.findAll();
    }

    @Override
    public SocietyTree getSocietyTreeByID(Long id) {
        return societyTreeRepository.findById(id).orElseThrow();
    }

    @Override
    public SocietyTree getSocietyTreeByName(String treeName) {
        return societyTreeRepository.findByTreeName(treeName);
    }

    @Override
    public SocietyTree getSocietyTreeByLineageName(String lineageName) {
        return societyTreeRepository.findByPedigrees_LineageName(lineageName);
    }

    @Override
    public Boolean deleteSocietyTreeByID(Long id) {
        societyTreeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public SocietyTree saveSocietyTree(SocietyTree societyTree) {
//        if(personRepository.countByName(family.getPerson().getName().toLowerCase()) >= 1) {
//            throw new DataInsertionException("Person name");
//        }
        return societyTreeRepository.save(societyTree);
    }

    @Override
    public SocietyTree updateSocietyTree(Long id, SocietyTree societyTree) {
        SocietyTree newSocietyTree = societyTreeRepository.findById(id).orElseThrow();

        if(societyTree.getTreeName() != null && newSocietyTree.getTreeName() != societyTree.getTreeName()) {
            newSocietyTree.setTreeName(societyTree.getTreeName());
        }

        if(societyTree.getPedigrees() != null && newSocietyTree.getPedigrees() != societyTree.getPedigrees()) {
            newSocietyTree.setPedigrees(societyTree.getPedigrees());
        }

//        if(family.getFamilyName() != null && newFamily.getFamilyName() != family.getFamilyName()) {
//            newFamily.setFamilyName(family.getFamilyName());
//        }
//
//        if(family.getFatherName() != null && newFamily.getFatherName() != family.getFatherName()) {
//            newFamily.setFatherName(family.getFatherName());
//        }
//
//        if(family.getMotherName() != null && newFamily.getMotherName() != family.getMotherName()) {
//            newFamily.setMotherName(family.getMotherName());
//        }
//
//        if(family.getChildren() != null && newFamily.getChildren() != family.getChildren()) {
//            newFamily.setChildren(family.getChildren());
//        }
//
//        if(family.getSiblings() != null && newFamily.getSiblings() != family.getSiblings()) {
//            newFamily.setSiblings(family.getSiblings());
//        }

//        if(family.getPerson() != null && newFamily.getPerson() != family.getPerson()) {
//            newFamily.setPerson(checkPersonForUpdate(family.getPerson(), newFamily.getPerson()));
//        }

//        if(family.getSpouses() != null && newFamily.getSpouses() != family.getSpouses()) {
//            newFamily.setSpouses(family.getSpouses());
//        }
//
//        if(family.getLineage() != null && newFamily.getLineage() != family.getLineage()) {
//            newFamily.setLineage(family.getLineage());
//        }
//
//        if(family.getUrlCoatOfArms() != null && newFamily.getUrlCoatOfArms() != family.getUrlCoatOfArms()) {
//            newFamily.setUrlCoatOfArms(family.getUrlCoatOfArms());
//        }
//
//        if(family.getIsBastard() != null && newFamily.getIsBastard() != family.getIsBastard()) {
//            newFamily.setIsBastard(family.getIsBastard());
//        }

        return societyTreeRepository.save(newSocietyTree);
    }

    @Override
    public SocietyTree modifySocietyTree(Long id, SocietyTree societyTree) {
        SocietyTree newSocietyTree = societyTreeRepository.findById(id).orElseThrow();
        newSocietyTree.setTreeName(societyTree.getTreeName());
        newSocietyTree.setPedigrees(societyTree.getPedigrees());
        return societyTreeRepository.save(newSocietyTree);
    }

    //Helper Methods
//    private Person checkPersonForUpdate(Person person, Person newPerson) {
//        if(person.getName() != null && newPerson.getName() != person.getName()) {
//            newPerson.setName(person.getName());
//        }
//
//        if(person.getNation() != null && newPerson.getNation() != person.getNation()) {
//            newPerson.setNation(person.getNation());
//        }
//
//        if(person.getReligion() != null && newPerson.getReligion() != person.getReligion()) {
//            newPerson.setReligion(person.getReligion());
//        }
//
//        if(person.getRace() != null && newPerson.getRace() != person.getRace()) {
//            newPerson.setRace(person.getRace());
//        }
//
//        if(person.getGender() != null && newPerson.getGender() != person.getGender()) {
//            newPerson.setGender(person.getGender());
//        }
//
//        if(person.getJob() != null && newPerson.getJob() != person.getJob()) {
//            newPerson.setJob(person.getJob());
//        }
//
//        if(person.getBirthYear() != null && newPerson.getBirthYear() != person.getBirthYear()) {
//            newPerson.setBirthYear(person.getBirthYear());
//        }
//
//        if(person.getDeathYear() != null && newPerson.getDeathYear() != person.getDeathYear()) {
//            newPerson.setDeathYear(person.getDeathYear());
//        }
//
//        if(person.getImageURL() != null && newPerson.getImageURL() != person.getImageURL()) {
//            newPerson.setImageURL(person.getImageURL());
//        }
//
//        if(person.getBiography() != null && newPerson.getBiography() != person.getBiography()) {
//            newPerson.setBiography(person.getBiography());
//        }
//
//        if(person.getTitle() != null && newPerson.getTitle() != person.getTitle()) {
//            newPerson.setTitle(person.getTitle());
//        }
//
//        return newPerson;
//    }
}
