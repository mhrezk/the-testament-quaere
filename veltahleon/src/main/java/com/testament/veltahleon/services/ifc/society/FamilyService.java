package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.Family;

import java.util.Collection;

public interface FamilyService {
    Collection<Family> getFamilyByCommunityName(String communityName);
    Boolean deleteByID(String id);
    Family saveFamily(Family family);
    Family updateFamily(String id, Family family);
    Family modifyFamily(String id, Family family);
}
