package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.FamilyDTO;
import com.testament.veltahleon.enumerations.Gender;
import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.services.ifc.society.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FamilyMapper {

    @Autowired
    private CommunityService communityService;

    public FamilyDTO convertToDTO(Family family) {
        return FamilyDTO.builder()
                .id(family.getId())
                .fatherID(family.getFatherID())
                .motherID(family.getMotherID())
                .spouseIDs(family.getSpouseIDs())
                .firstName(family.getFirstName())
                .secondName(family.getSecondName())
                //.race(family.getRace().getName())
                .gender(String.valueOf(family.getGender()))
                //.nation(family.getNation().getName())
                //.age(family.getAge())
                .communityName(family.getCommunity().getName())
                .stringID(family.getStringID())
                .imageURL(family.getImageURL())
                .build();
    }

    public Family convertToEntity(FamilyDTO familyDTO) {
        Family family = new Family();
        family.setId(familyDTO.getId());
        family.setStringID(familyDTO.getStringID());
        family.setFirstName(familyDTO.getFirstName());
        family.setSecondName(familyDTO.getSecondName());
        //family.setAge(familyDTO.getAge());
        family.setMotherID(familyDTO.getMotherID());
        family.setFatherID(familyDTO.getFatherID());
        family.setImageURL(familyDTO.getImageURL());
        //family.setNation(personFacade.getNation(familyDTO.getNation()));
        //family.setRace(personFacade.getRace(familyDTO.getRace()));
        family.setCommunity(communityService.getCommunityByName(familyDTO.getCommunityName()));
        family.setGender(Gender.valueOf(familyDTO.getGender()));
        family.setSpouseIDs(familyDTO.getSpouseIDs());
        return family;
    }
}
