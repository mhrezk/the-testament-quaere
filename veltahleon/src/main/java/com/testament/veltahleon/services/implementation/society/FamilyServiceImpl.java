package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.repositories.society.FamilyRepository;
import com.testament.veltahleon.services.ifc.society.FamilyService;
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
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Collection<Family> getFamiliesWithPagination(int pageNumber, int numberOfRecords) {
        return familyRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Family> getFamilies() {
        return familyRepository.findAll();
    }

    @Override
    public Collection<Family> getFamiliesByLineage(String lineage) {
        return familyRepository.findByLineage(lineage);
    }

    @Override
    public Family getFamilyByID(Long id) {
        return familyRepository.findById(id).orElseThrow();
    }

    @Override
    public Family getFamilyByFamilyName(String personName) {
        return familyRepository.findByFamilyName(personName);
    }

    @Override
    public Boolean deleteFamilyByID(Long id) {
        familyRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Family saveFamily(Family family) {
//        if(personRepository.countByName(family.getPerson().getName().toLowerCase()) >= 1) {
//            throw new DataInsertionException("Person name");
//        }
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(Long id, Family family) {
        Family newFamily = familyRepository.findById(id).orElseThrow();

        if(family.getFamilyName() != null && newFamily.getFamilyName() != family.getFamilyName()) {
            newFamily.setFamilyName(family.getFamilyName());
        }

        if(family.getFatherName() != null && newFamily.getFatherName() != family.getFatherName()) {
            newFamily.setFatherName(family.getFatherName());
        }

        if(family.getMotherName() != null && newFamily.getMotherName() != family.getMotherName()) {
            newFamily.setMotherName(family.getMotherName());
        }

        if(family.getChildren() != null && newFamily.getChildren() != family.getChildren()) {
            newFamily.setChildren(family.getChildren());
        }

        if(family.getSiblings() != null && newFamily.getSiblings() != family.getSiblings()) {
            newFamily.setSiblings(family.getSiblings());
        }

//        if(family.getPerson() != null && newFamily.getPerson() != family.getPerson()) {
//            newFamily.setPerson(checkPersonForUpdate(family.getPerson(), newFamily.getPerson()));
//        }

        if(family.getSpouses() != null && newFamily.getSpouses() != family.getSpouses()) {
            newFamily.setSpouses(family.getSpouses());
        }

        if(family.getLineage() != null && newFamily.getLineage() != family.getLineage()) {
            newFamily.setLineage(family.getLineage());
        }

        if(family.getUrlCoatOfArms() != null && newFamily.getUrlCoatOfArms() != family.getUrlCoatOfArms()) {
            newFamily.setUrlCoatOfArms(family.getUrlCoatOfArms());
        }

        if(family.getIsBastard() != null && newFamily.getIsBastard() != family.getIsBastard()) {
            newFamily.setIsBastard(family.getIsBastard());
        }

        return familyRepository.save(newFamily);
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
