package com.testament.veltahleon.services.implementation.society;

import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.repositories.society.FamilyRepository;
import com.testament.veltahleon.services.ifc.society.FamilyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyRepository familyRepository;

    @Override
    public Collection<Family> getFamilyByCommunityName(String communityName) {
        return familyRepository.findByCommunity_Name(communityName);
    }

    @Override
    public Boolean deleteByID(String id) {
        familyRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Family saveFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family updateFamily(String id, Family family) {
        Family newFamily = familyRepository.findById(id).orElseThrow();

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
    public Family modifyFamily(String id, Family family) {
        Family newFamily = familyRepository.findById(id).orElseThrow();
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
}
