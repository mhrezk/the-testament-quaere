package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.repositories.society.CommunityRepository;
import com.testament.veltahleon.repositories.society.FamilyRepository;
import com.testament.veltahleon.services.ifc.society.FamilyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public Collection<Family> getFamiliesByCommunityName(String communityName) {
        return familyRepository.findByCommunity_Name(communityName);
    }

    @Override
    public Collection<Family> saveFamilies(Collection<Family> families, Integer size, Long communityID) {
        familyRepository.deleteAll();
        communityRepository.updateCommunitySize(size, communityID);
        return familyRepository.saveAll(families);
    }

    @Override
    public Boolean deleteFamilyByID(Long id) {
        familyRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteAllFamilies() {
        familyRepository.deleteAll();
        return Boolean.TRUE;
    }

    @Override
    public Family getFamilyByID(Long id) {
        return familyRepository.findById(id).orElseThrow();
    }

    @Override
    public Family getFamilyByStringID(String id) {
        return familyRepository.findByStringID(id);
    }

    @Override
    public Family saveFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(Long id, Family family) {
        Family newFamily = familyRepository.findById(id).orElseThrow();

        if(family.getStringID() != null && newFamily.getStringID() != family.getStringID()) {
            newFamily.setStringID(family.getStringID());
        }

        if(family.getFirstName() != null && newFamily.getFirstName() != family.getFirstName()) {
            newFamily.setFirstName(family.getFirstName());
        }

        if(family.getSecondName() != null && newFamily.getSecondName() != family.getSecondName()) {
            newFamily.setSecondName(family.getSecondName());
        }

        if(family.getImageURL() != null && newFamily.getImageURL() != family.getImageURL()) {
            newFamily.setImageURL(family.getImageURL());
        }

        if(family.getFatherID() != null && newFamily.getFatherID() != family.getFatherID()) {
            newFamily.setFatherID(family.getFatherID());
        }

        if(family.getMotherID() != null && newFamily.getMotherID() != family.getMotherID()) {
            newFamily.setMotherID(family.getMotherID());
        }

        if(family.getSpouseIDs() != null && newFamily.getSpouseIDs() != family.getSpouseIDs()) {
            newFamily.setSpouseIDs(family.getSpouseIDs());
        }

        if(family.getCommunity() != null && newFamily.getCommunity() != family.getCommunity()) {
            newFamily.setCommunity(family.getCommunity());
        }

        if(family.getGender() != null && newFamily.getGender() != family.getGender()) {
            newFamily.setGender(family.getGender());
        }

        return familyRepository.save(newFamily);
    }

    @Override
    public Family modifyFamily(Long id, Family family) {
        Family newFamily = familyRepository.findById(id).orElseThrow();
        newFamily.setStringID(family.getStringID());
        newFamily.setFirstName(family.getFirstName());
        newFamily.setSecondName(family.getSecondName());
        newFamily.setFatherID(family.getFatherID());
        newFamily.setMotherID(family.getMotherID());
        newFamily.setGender(family.getGender());
        newFamily.setSpouseIDs(family.getSpouseIDs());
        newFamily.setImageURL(family.getImageURL());
        newFamily.setCommunity(family.getCommunity());
        return familyRepository.save(newFamily);
    }

    @Override
    public Long countFamiliesByCommunityName(String name) {
        return familyRepository.countByCommunity_Name(name);
    }
}
