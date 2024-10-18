package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Family;

import java.util.Collection;

public interface FamilyService {
    Collection<Family> getFamiliesByCommunityName(String communityName);
    Collection<Family> saveFamilies(Collection<Family> families, Integer size, Long communityID);
    Boolean deleteFamilyByID(Long id);
    Boolean deleteAllFamilies();
    Family getFamilyByID(Long id);
    Family getFamilyByStringID(String id);
    Family saveFamily(Family family);
    Family updateFamily(Long id, Family family);
    Family modifyFamily(Long id, Family family);
    Long countFamiliesByCommunityName(String communityName);
}
